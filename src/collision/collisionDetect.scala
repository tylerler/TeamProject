package collision

object collisionDetect {
  def detectCollision(object1: collision, object2: collision): Boolean = {
      if (object1.x - object2.x <= 5 && object1.x - object2.x >= 0 || object2.x - object1.x <= 5 && object2.x - object1.x >= 0 || object1.y - object2.y <= 5 && object1.y - object2.y >= 0 || object2.y - object1.y <= 5 && object2.y - object1.y >= 0) {
        return true
    }
    false
  }
}
