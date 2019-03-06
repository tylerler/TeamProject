package HealthTrack

import org.scalatest.FunSuite
import Health._

class testHealth extends FunSuite {
  test("Use many test cases for each category") {
    //test
    val damageTest: HealthTrack = new damageTaken
    damageTest.currentHealth = 100
    assert(damageTest.takeDamage() == 95)
    val healthTest: HealthTrack = new healthHealed
    healthTest.currentHealth = 95
    assert(healthTest.healHealth() == 100)
    val healthTest2: HealthTrack = new healthHealed
    healthTest2.currentHealth = 100
    assert(healthTest2.healHealth() == 100)
    val shieldHTest: HealthTrack = new shieldRepair
    shieldHTest.currentShield = 45
    assert(shieldHTest.shieldRepair() == 50)
    val shieldHTest2: HealthTrack = new shieldRepair
    shieldHTest2.currentShield = 50
    assert(shieldHTest2.shieldRepair() == 50)
    val shieldDTest: HealthTrack = new shieldDamage
    shieldDTest.currentShield = 45
    assert(shieldDTest.shieldDamage() == 40)
    val shieldDTest2: HealthTrack = new shieldDamage
    shieldDTest2.currentShield = 0
    assert(shieldDTest2.shieldDamage() == 0)
  }
}
