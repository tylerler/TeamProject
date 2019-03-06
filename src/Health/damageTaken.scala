package Health

class damageTaken extends HealthTrack {
  this.name = "speedPowerUp"

  override def takeDamage(): Double = {
    var newHealth: Double = this.currentHealth
    if(newHealth < 5){
      newHealth = 100
    }
    else{
      newHealth = this.currentHealth - 5
    }
    newHealth
  }

  override def healHealth(): Double = {
    0.0
  }

  override def shieldDamage(): Double = {
    0.0
  }

  override def shieldRepair(): Double = {
    0.0
  }
}
