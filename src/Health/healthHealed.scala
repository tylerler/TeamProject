package Health

class healthHealed extends HealthTrack {
  this.name = "speedPowerUp"

  override def takeDamage(): Double = {
    0.0
  }

  override def healHealth(): Double = {
    var newHealth: Double = this.currentHealth
    if(newHealth > 95){
      newHealth = 100
    }
    else{
      newHealth = this.currentHealth + 5
    }
    newHealth
  }

  override def shieldDamage(): Double = {
    0.0
  }

  override def shieldRepair(): Double = {
    0.0
  }
}
