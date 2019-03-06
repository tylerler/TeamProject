package PowerUps

class shipDown extends testing{
  this.name = "speedPowerUp"

  override def shipSpeedUp(): Double = {
    this.currentSpeed
  }

  override def shipSpeedDown(): Double = {
    var newSpeed: Double = this.currentSpeed - 5.0
    if(newSpeed < 5){
      newSpeed = 5
    }
    else{
      newSpeed
    }
    newSpeed
  }

  override def speedOfBulletUp(): Double = {
    this.bulletSpeed
  }

  override def speedOfBulletDown(): Double = {
    this.bulletSpeed
  }
}
