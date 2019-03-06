package PowerUps

class shipUp extends testing{
  this.name = "speedPowerUp"

  override def shipSpeedUp(): Double = {
    var newSpeed: Double = this.currentSpeed + 5.0
    newSpeed
  }

  override def shipSpeedDown(): Double = {
    this.currentSpeed
  }

  override def speedOfBulletUp(): Double = {
    this.bulletSpeed
  }

  override def speedOfBulletDown(): Double = {
    this.bulletSpeed
  }
}
