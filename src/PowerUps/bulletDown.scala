package PowerUps

class bulletDown extends testing {
  this.name = "speedPowerUp"

  override def shipSpeedUp(): Double = {
    this.currentSpeed
  }

  override def shipSpeedDown(): Double = {
    this.currentSpeed
  }

  override def speedOfBulletUp(): Double = {
    this.bulletSpeed
  }

  override def speedOfBulletDown(): Double = {
    var newSpeed: Double = this.bulletSpeed - 5.0
    if(newSpeed < 5){
      newSpeed = 5
    }
    else{
      newSpeed
    }
    newSpeed
  }
}
