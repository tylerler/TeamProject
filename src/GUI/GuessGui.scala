package GUI

import Sample.SampleGUI.{button, inputDisplay, outputDisplay}
import javafx.event.ActionEvent
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.VBox

object GuessGui extends  JFXApp{
  val inputDisplay: TextField = new TextField {
    style = "-fx-font: 18 ariel;"
  }
  val outputDisplay: TextField = new TextField {
    editable = false
    style = "-fx-font: 18 ariel;"
  }
  val outputDisplay2: TextField = new TextField {
    editable = false
    style = "-fx-font: 18 ariel;"
  }


  val alternateButton: Button = new Button {
    minWidth = 100
    minHeight = 100
    style = "-fx-font: 28 ariel;"
    text = "Guess untill correct"
    onAction = new ButoonListener2(inputDisplay, outputDisplay,outputDisplay2)

  }



  this.stage = new PrimaryStage {
    title = "GUI Example"
    scene = new Scene() {
      content = List(
        new VBox() {
          children = List(inputDisplay, alternateButton,outputDisplay,outputDisplay2)
        }
      )
    }
  }
}
