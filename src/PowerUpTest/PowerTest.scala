package PowerUpTest

import org.scalatest.FunSuite
import PowerUps._

class PowerTest extends FunSuite {
  test("Use many test cases for each category") {
    val ShipUpTest: testing = new shipUp
    ShipUpTest.currentSpeed = 10
    assert(ShipUpTest.shipSpeedUp() == 15)
    val ShipDownTest: testing = new shipDown
    ShipDownTest.currentSpeed = 15
    assert(ShipDownTest.shipSpeedDown() == 10)
    val ShipDownTest2: testing = new shipDown
    ShipDownTest2.currentSpeed = 9
    assert(ShipDownTest2.shipSpeedDown() == 5)
    val ShipDownTest3: testing = new shipDown
    ShipDownTest3.bulletSpeed = 5
    assert(ShipDownTest3.shipSpeedDown() == 5)
    val BulletUpTest: testing = new bulletUp
    BulletUpTest.bulletSpeed = 25
    assert(BulletUpTest.speedOfBulletUp() == 30)
    val BulletDownTest: testing = new bulletDown
    BulletDownTest.bulletSpeed = 15
    assert(BulletDownTest.speedOfBulletDown() == 10)
    val BulletDownTest2: testing = new bulletDown
    BulletDownTest2.bulletSpeed = 8
    assert(BulletDownTest2.speedOfBulletDown() == 5)
    val BulletDownTest3: testing = new bulletDown
    BulletDownTest2.currentSpeed = 5
    assert(BulletDownTest2.speedOfBulletDown() == 5)
  }
}
