import bottle
playerList = []


@bottle.route('/join')
def join(username):
    playerList.append(username)




@bottle.route('/leave')
def leave(username):
    for x in playerList:
        if x == username:
            playerList.remove(username)
#tes

@bottle.route('/players')
def players():
    return playerList




bottle.run(host="0.0.0.0", port=8080, debug=True)
