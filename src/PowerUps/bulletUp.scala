package PowerUps

class bulletUp extends testing {
  this.name = "speedPowerUp"

  override def shipSpeedUp(): Double = {
    this.currentSpeed
  }

  override def shipSpeedDown(): Double = {
    this.currentSpeed
  }

  override def speedOfBulletUp(): Double = {
    var newSpeed: Double = this.bulletSpeed + 5.0
    newSpeed
  }

  override def speedOfBulletDown(): Double = {
    this.bulletSpeed
  }
}
