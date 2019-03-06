playerList = []

def join(username):
    playerList.append(username)

def leave(username):
    for x in playerList:
        if x == username:
            playerList.remove(username)
def players():
    return playerList

print(join("hi"))
print(leave("hi"))
print(players())
