package CollisionTests

import org.scalatest.FunSuite
import collision._

class testCollision extends FunSuite{
  var test1 = new collision(10, 4)
  var test2 = new collision(5, 9)
  var test3 = new collision(15, 20)
  var test4 = new collision(5, 4)
  var test5 = new collision(0, 0)
  assert(collisionDetect.detectCollision(test1, test2) == true)
  assert(collisionDetect.detectCollision(test2, test3) == false)
  assert(collisionDetect.detectCollision(test1, test4) == true)
  assert(collisionDetect.detectCollision(test5, test4) == true)
  assert(collisionDetect.detectCollision(test5, test3) == false)
}
