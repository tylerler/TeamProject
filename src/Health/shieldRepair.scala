package Health

class shieldRepair extends HealthTrack {
  this.name = "speedPowerUp"

  override def takeDamage(): Double = {
    0.0
  }

  override def healHealth(): Double = {
    0.0
  }

  override def shieldDamage(): Double = {
    0.0
  }

  override def shieldRepair(): Double = {
    var newShield: Double = this.currentShield
    if(newShield > 45){
      newShield = 50
    }
    else{
      newShield = this.currentShield + 5
    }
    newShield
  }
}
