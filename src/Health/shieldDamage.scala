package Health

class shieldDamage extends HealthTrack {
  this.name = "speedPowerUp"

  override def takeDamage(): Double = {
    0.0
  }

  override def healHealth(): Double = {
    0.0
  }

  override def shieldDamage(): Double = {
    var newShield: Double = this.currentShield
    if(newShield < 5){
      newShield = 0
    }
    else{
      newShield = this.currentShield - 5
    }
    newShield
  }

  override def shieldRepair(): Double = {
    0.0
  }
}
