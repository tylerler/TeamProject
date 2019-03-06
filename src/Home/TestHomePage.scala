package Home

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

object TestHomePage extends JFXApp {
  val text: Text = new Text(){
    text = "Plane"
    style = "-fx-font-size: 200pt"
    fill = new LinearGradient(
      endX = 0,
      stops = Stops(PaleGreen, SeaGreen))
    effect = new DropShadow {
      color = DodgerBlue
      radius = 100
      spread = .5
    }
  }
  text.layoutX = 130
  text.layoutY = 210

  val text2: Text = new Text(){
    text = "Battleground!!!"
    style = "-fx-font-size: 100pt"
    fill = new LinearGradient(
      endX = 0,
      stops = Stops(Cyan, DodgerBlue)
    )
    effect = new DropShadow {
      color = DodgerBlue
      radius = 100
      spread = .5
    }
  }
  text2.layoutX =50
  text2.layoutY= 390

  val button: Button = new Button(){
    minWidth = 50
    minHeight = 50
    style = "-fx-font: 28 ariel;"
    text = "Start Game"
  }
  button.translateX = 420
  button.translateY = 510

  val nameDisplay: TextField = new TextField {
    editable = false
    style = "-fx-font: 18 ariel;"
    text = "UserName"
  }
  nameDisplay.translateX = 300
  nameDisplay.translateY = 450

  val userName: TextField = new TextField {
    style = "-fx-font: 18 ariel;"
  }
  userName.translateX = 510
  userName.translateY = 450

  nameDisplay.translateX = 290
  nameDisplay.translateY = 450
  stage = new PrimaryStage {
    title = "HomePage"
    scene = new Scene(1000,1000) {

      fill = Black
      content = List(text,text2,
        new HBox() {
          children = List(button)
        },
        userName,
        nameDisplay
      )
    }
  }


}
