//code for express, should be the only time using it
var express = require('express');
var app = express();
var serv = require('http').Server(app);

//if my website is site.com:2000
//And if the url is / then it does that function
app.get('/', function(req, res) {
  res.sendFile(__dirname + '/client/index.html');
});

//if it has then it will find everything after site/example
app.use('/client',express.static(__dirname + "client"));

serv.listen(2000); //listen to port 2000
console.log("Server Starting");
//end
// player and socket list
var SOCKET_LIST = {};
var PLAYER_LIST = {};

//going to be a bullet, should make it a class, has a lot of similarity to player
var Entity = function(){
  var self = {
    x:250,
    y:250,
    entitySpeedX:0,
    entitySpeedY:0,
    id:""
  }
  //entity updator
  self.update = function(){
    self.updatePosition();
  }
  //updates speed
  self.updatePosition = function(){
    self.x = self.x + self.entitySpeedX;
    self.y = self.y + self.entitySpeedY;
  }
  //get the distance, for collision
  self.getDistance = function(point){
    return Math.sqrt(Math.pow(self.x-point.x,2) + Math.pow(self.y-point.y,2))
  }
  return self;
}

//this is the player, I should make it into a class but nahhhh lol
var Player = function(id){
  //a player is an entity but has additional features
  var self = Entity();
  self.id = Math.random();
  self.number = "My id:" + Math.floor(10 * Math.random()); // every socket will have a number between 0-1, used to se a player
  //keypress states
  self.keyPressRight = false;
  self.keyPressLeft= false;
  self.keyPressUp = false;
  self.keyPressDown = false;
  self.mouseClick = false;
  self.mouseAngle = 0;
  self.maxSpeed = 10;

//updates the entity(updatePosition) and updates the speed, overriding on crack
  var super_update = self.update;

  self.update = function(){
    //updates
    self.updateSpeed();
    super_update();

    //if mouse is clicked
    if(self.mouseClick){
      //for(var i = 3;i<3; i++){self.shootBullet(i* 10 + self.mouseAngle); //calls shootbullet}
      self.shootBullet(self.mouseAngle); //calls shootbullet
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
  //adds player to player dict
  Player.list[id] = self;

  return self;
}

//player list
Player.list = {};

//when a player connects this is will be called
Player.onConnect = function(socket){
  //creates a new player
  var player = Player(socket.id);
      //IMPORTANT!
      //This function updates the player position after being called from the client!
      // === means its type and value have to be equal to eachother. ex 5 === 5, true, "5" === 5, false
      socket.on('keyPress',function(data){
        if(data.inputId === 'left')
            player.keyPressLeft = data.state;
        else if(data.inputId === 'right')
            player.keyPressRight = data.state;
        else if(data.inputId === 'up')
            player.keyPressUp = data.state;
        else if(data.inputId === 'down')
            player.keyPressDown = data.state;
        else if(data.inputId === 'attack')
            player.mouseClick = data.state;
        else if(data.inputId === 'mouseAngle')
            player.mouseAngle = data.state;
      });
}
//when a player disconnect
Player.onDisconnect = function(socket){
  //delete player when disconnect
  delete Player.list[socket.id]
}
//updates the player, will be called every 60 frames
Player.update = function(){
  //contain all players from the Game
  var package = [];
  //add player from PLAYER_LIST
  for(var i in Player.list){

    var player = Player.list[i];
    player.update();
    //all data of all players
    package.push({
          x:player.x,
          y:player.y,
          number:player.number
            });
  }
  return package;
}

//Bullet!
var Bullet = function(parent,angle){
  //is an Entity
  var self = Entity();
  self.id = Math.random(); //gives a bullet a unique id
  self.entitySpeedX = Math.cos(angle/180 * Math.PI) * 10;
  self.entitySpeedY = Math.sin(angle/180 * Math.PI) * 10;
  self.parent = parent;
  self.timer = 0;
  //used for collision
  self.toRemove = false;

  var super_update = self.update;
  //override and then add self.update
  self.update = function(){
    //remove bullet afte 100 frams
    if(self.timer++ > 100)
      self.toRemove = true;
      super_update();
    //goes through all the players
    for(var i in Player.list){
      var play = Player.list[i]
      //check if there is a collision
      if(self.getDistance(play)< 32 && self.parent != play.id){
        //eventually need to come back
        self.toRemove = true
      }

    }
  }
  //add bullet to list
  Bullet.list[self.id] = self;
  return self;
}

Bullet.list = {};

//updates the bullet
Bullet.update = function(){
  var package = [];
  //add bullet from bullet
  for(var i in Bullet.list){
    var bullet = Bullet.list[i];
    bullet.update();
    //removes bullet if it collides with a player
    if(bullet.toRemove){
      delete Bullet.list[i];
    }
    else{
    //all data of all the Bullet
    package.push({
          x:bullet.x,
          y:bullet.y,
          });
    }
  }

  return package;
}
 //make sure to change, if not then people can crash/cheat game
var DEBUG = true;

//return all object in socket library
var io = require('socket.io')(serv,{});
io.sockets.on('connection', function(socket){
    //every time is refreshed, a client is assign a socket
    //this gives that client a unique socket id
    socket.id = Math.random();

    SOCKET_LIST[socket.id] = socket; // add socket to list
      Player.onConnect(socket);

    //only when player is signed it it will create a player
    // socket.on('signIn',function(data){
    //   if(data.username == 'bob' && data.password == 'asd'){
    //       Player.onConnect(socket);
    //       socket.emit('signInResponse',{success:true});
    //   }
    //   else {
    //     socket.emit('signInResponse',{success:false});
    //   }

    //});

//when a person disconnect, don't need to use in html, automaticly does it.
    socket.on('disconnect',function(){
        delete SOCKET_LIST[socket.id];
        Player.onDisconnect(socket);
    });
    //gets data from the server and then sends back to the client, but addes it to chat
    socket.on('sendMSGToServer',function(data){
      var playerName = ("" + socket.id).slice(2,7);
      for(var i in SOCKET_LIST){
        SOCKET_LIST[i].emit('addToChat',playerName + ': ' + data);
      }
    });
    //used to debug
    socket.on('evalServer',function(data){
      if(!DEBUG){
        return;
      }
      var res = eval(data); //evalue the string
      socket.emit('evalAnswer',res); // sends html
    });
});

//fps, main update function
setInterval(function(){
  var pack = {
    player:Player.update(),
    bullet: Bullet.update()
  }
  //sends players position
  for(var i in SOCKET_LIST){
    var socket = SOCKET_LIST[i];
    socket.emit('newPositions',pack);
  }
},1000/25);
