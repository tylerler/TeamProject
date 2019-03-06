package GUI

import javafx.event.{ActionEvent, EventHandler}
import scalafx.scene.control.TextField
import scala.util.Random

class ButoonListener2(inputDisplay: TextField, outputDisplay: TextField, outputDisplay2: TextField) extends EventHandler[ActionEvent] {
  var random = scala.util.Random.nextInt(100)

  override def handle(event: ActionEvent): Unit = {
    var num: Int = inputDisplay.text.value.toInt
    var check = this.guessNum(num)
    outputDisplay.text.value = check
    outputDisplay2.text.value = cool()
  }

    var count:Int = 0
    def cool(): String ={
      count += 1
      outputDisplay2.text.value = count.toString
      return count.toString
    }

  def guessNum(num: Int): String = {
    var output = ""
    if(num > random){
      output = "Lower"
    }
    if(num < random){
      output = "Higher"
    }
    if(num == random){
      output = "Correct"
    }
    output
  }

}


