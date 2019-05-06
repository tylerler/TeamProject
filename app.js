//code for express, should be the only time using it
var express = require('express');
var app = express();
var serv = require('http').Server(app);

//if my website is site.com:2000
//And if the url is / then it does that function
app.get('/',function(req, res) {
    res.sendFile(__dirname + '/client/index.html');
});
//if it has then it will find everything after site/example
app.use('/client',express.static(__dirname + '/client'));

//listen to port 2000
serv.listen(process.env.PORT || 2000);
console.log("Server started.");

var SOCKET_LIST = {};
var initPack = {player:[],bullet:[]};
var removePack = {player:[],bullet:[]};


//going to be a bullet, should make it a class, has a lot of similarity to player
var Entity = function(){
    var self = {
        x:250,
        y:250,
        entitySpeedX:0,
        entitySpeedY:0,
        id:"",
    }
    //entity updator
    self.update = function(){
        self.updatePosition();
    }
    //updates speed
    self.updatePosition = function(){
        self.x += self.entitySpeedX;
        self.y += self.entitySpeedY;
    }
    //get the distance, for collision
    self.getDistance = function(point){
        return Math.sqrt(Math.pow(self.x-point.x,2) + Math.pow(self.y-point.y,2));
    }
    return self;
}

//this is the player, I should make it into a class but nahhhh lol
var Player = function(id){
    //a player is an entity but has additional features
    var self = Entity();
    self.id = id;
    self.number = "" + Math.floor(10 * Math.random());

    //keypress states
    self.keyPressRight = false;
    self.keyPressLeft = false;
    self.keyPressUp = false;
    self.keyPressDown = false;
    self.mouseClick = false;
    self.mouseAngle = 0;
    self.maxSpeed = 10;
    self.hp = 10;
    self.hpMax = 10;
    self.score =0;
   //updates the entity(updatePosition) and updates the speed
    var super_update = self.update;
    //updates
    self.update = function(){
        self.updateSpeed();
        super_update();

        if(self.mouseClick){
            self.shootBullet(self.mouseAngle);
        }
    }

    //bullet spawns ontop of player and shoots
    self.shootBullet = function(angle){
        var bullet = Bullet(self.id,angle);
        bullet.x = self.x;
        bullet.y = self.y;
    }
   //this will be called every frame, updates player speed
    self.updateSpeed = function(){
         if(self.keyPressRight){
             self.entitySpeedX = self.maxSpeed;
          }
         else if(self.keyPressLeft){
             self.entitySpeedX = -self.maxSpeed;
          }

         else{
             self.entitySpeedX = 0;
          }
         if(self.keyPressUp){
             self.entitySpeedY = -self.maxSpeed;
           }
         else if(self.keyPressDown){
             self.entitySpeedY = self.maxSpeed;
           }
         else{
             self.entitySpeedY = 0;
           }
    }
    self.getInitPack = function(){
        return{
        id:self.id,
        x:self.x,
        y:self.y,
        number:self.number,
        hp:self.hp,
        hpMax:self.hpMax,
        score:self.score,
        };
    }
    self.getUpdatePack = function(){
        return{
        id:self.id,
        x:self.x,
        y:self.y,
        hp:self.hp,
        score:self.score,
        };
    }
      //adds player to player dict
    Player.list[id] = self;
    initPack.player.push(self.getInitPack());
    return self;
}
//player list
Player.list = {};
//when a player connects this is will be called
Player.onConnect = function(socket){
          //IMPORTANT!
      //This function updates the player position after being called from the client!
      // === means its type and value have to be equal to eachother. ex 5 === 5, true, "5" === 5, false
    var player = Player(socket.id);
      socket.on('keyPress',function(info){
        if(info.inputId === 'left'){

            player.keyPressLeft = info.state;
          }
        else if(info.inputId === 'right'){

            player.keyPressRight = info.state;

          }
        else if(info.inputId === 'up'){

            player.keyPressUp = info.state;

          }
        else if(info.inputId === 'down'){

            player.keyPressDown = info.state;

          }
        else if(info.inputId === 'attack'){

            player.mouseClick = info.state;
          }
        else if(info.inputId === 'mouseAngle'){
            player.mouseAngle = info.state;
          }
      });

    var players = []
    for(var i in Player.list){
        players.push(Player.list[i].getInitPack());
    }
    var bullets = []
    for(var i in Bullet.list){
        bullets.push(Bullet.list[i].getInitPack());
    }
    socket.emit('init',{
        player:players,
        bullet:bullets,
    });
}

//when a player disconnect
Player.onDisconnect = function(socket){
    delete Player.list[socket.id];
    removePack.player.push(socket.id);
}
//updates the player, will be called every 60 frames
Player.update = function(){
      //contain all players from the Game
    var package = [];

    for(var i in Player.list){

        var player = Player.list[i];
        //updates player
        player.update();
        //gets all the data from the player than pushes it to the list
        package.push(player.getUpdatePack());
    }
    return package;
}

 //this is the bullet
var Bullet = function(parent,angle){
    //also and entitiy
    var self = Entity();
    //give the bullet a unique id
    self.id = Math.random();
    //give a random speed
    self.entitySpeedX = Math.cos(angle/180*Math.PI) * 10;

    self.entitySpeedY = Math.sin(angle/180*Math.PI) * 10;
    //parent is the player, knows who the bullet is shooting from
    self.parent = parent;
    self.bullettimer = 0;
    self.toRemove = false;

    var super_update = self.update;
    //removes bullet a given interval
    self.update = function(){
        //remove bullet afte 100 frams
        if(self.bullettimer++ > 100)
            self.toRemove = true;

        super_update();

        for(var i in Player.list){
            var play = Player.list[i];
              //check if there is a collision
            if(self.getDistance(play) < 32 && self.parent !== play.id){
                  play.hp -= 1;

                if(play.hp <= 0){
                    var shooter = Player.list[self.parent];
                    if(shooter)
                        shooter.score += 1;
                    play.hp = play.hpMax;
                    //spawns after death
                    play.x = Math.random() * 500;
                    play.y = Math.random() * 500;
                }
                self.toRemove = true;

            }
        }
    }
    self.getInitPack = function(){
        return{
        id:self.id,
        x:self.x,
        y:self.y,
        };
    }
    self.getUpdatePack = function(){
        return{
        id:self.id,
        x:self.x,
        y:self.y,
        };
    }
    Bullet.list[self.id] = self;
    initPack.bullet.push(self.getInitPack());

    return self;
}
Bullet.list = {};

Bullet.update = function(){
    var pack = [];
    for(var i in Bullet.list){
        var bullet = Bullet.list[i];
        bullet.update();
        if(bullet.toRemove){
            delete Bullet.list[i];
            removePack.bullet.push(bullet.id);
        }
        else
            pack.push(bullet.getUpdatePack());
    }
    return pack;
}

 //hold all the users for the game
var USERS = {

}
//checks for valid password
var isValidPassword = function(data,cb){
    setTimeout(function(){
        cb(USERS[data.username] === data.password);
    },10);
}
//checks for username
var isUsernameTaken = function(data,cb){
    setTimeout(function(){
        cb(USERS[data.username]);
    },10);
}
//adds the user to the user list
var addUser = function(data,cb){
    setTimeout(function(){
        USERS[data.username] = data.password;
        cb();
    },10);
}
 //return all object in socket library
var io = require('socket.io')(serv,{});

io.sockets.on('connection', function(socket){
    //every time is refreshed, a client is assign a socket
    //this gives that client a unique socket id
    socket.id = Math.random();
    SOCKET_LIST[socket.id] = socket;
    //Player.onConnect(socket);
  //  only when player is signed it it will create a player
    socket.on('signIn',function(data){
        isValidPassword(data,function(res){
            if(res){
                Player.onConnect(socket);
                //console.log("Player connected");
                socket.emit('signInResponse',{success:true});
            } else {
                socket.emit('signInResponse',{success:false});
            }
        });
    });
    socket.on('signUp',function(data){
        isUsernameTaken(data,function(res){
            if(res){
                socket.emit('signUpResponse',{success:false});
            } else {
                addUser(data,function(){
                    socket.emit('signUpResponse',{success:true});
                });
            }
        });
    });

   //when a person disconnect, don't need to use in html, automaticly does it.

    socket.on('disconnect',function(){
        delete SOCKET_LIST[socket.id];
        Player.onDisconnect(socket);
    });

});

setInterval(function(){
    var pack = {
        player:Player.update(),
        bullet:Bullet.update(),
    }
    for(var i in SOCKET_LIST){
        var socket = SOCKET_LIST[i];
        socket.emit('init',initPack);
        socket.emit('update',pack);
        socket.emit('remove',removePack);
    }
    initPack.player = [];
    initPack.bullet = [];
    removePack.player = [];
    removePack.bullet = [];
},1000/25);
