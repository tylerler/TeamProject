package Health

abstract class HealthTrack {

    var currentHealth: Int = 100
    var currentShield: Int = 100
    var name: String = ""

    def takeDamage(): Double

    def healHealth(): Double

    def shieldDamage(): Double

    def shieldRepair(): Double


}
