package Game

import Game.GUI2D.{player, playerCircleRadius, sceneGraphics, windowWidth}
import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}


object Game2 extends JFXApp {
  val playerSpeed: Double = 10


  var sceneGraphics: Group = new Group {}
  val player: Rectangle = new Rectangle() {
    width = 60.0
    height = 40.0
    translateX = 60.0
    translateY = 10.0
    fill = Color.Blue

  }
  sceneGraphics.children.add(player)

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.translateY.value -= playerSpeed
      case "A" => player.translateX.value -= playerSpeed
      case "S" => player.translateY.value += playerSpeed
      case "D" => player.translateX.value += playerSpeed
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }

  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(800, 600) {
      content = List(sceneGraphics)

      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

    }
  }
}
