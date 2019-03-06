package GUI

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Polygon, Rectangle}

object Game extends JFXApp{
  var sceneGraphics: Group = new Group {}
  var playerX: Int = 600
  var playerY: Int = 600
  var playerSpeed: Int = 10


  val player = Polygon (playerX, playerY, playerX - 40,playerY +80,playerX + 40,playerY + 80)

  sceneGraphics.children.add(player)
  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "A" => player.rotate.value += 10
      case "D" => player.rotate.value -= 10
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }

  def keyPressed2(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "A" => player.translateX.value += 20
      case "D" => player.translateX.value -= 20
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }
  def keyPressed3(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "A" => player.translateY.value += 20
      case "D" => player.translateY.value -= 20
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }



  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(1910, 1060) {
      content = List(sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed2(event.getCode))
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed3(event.getCode))

    }
  }


  val update: Long => Unit = (time: Long) => {
    //player.translateY.value += -1

  }


  // Start Animations. Calls update 60 times per second (takes update as an argument)
  AnimationTimer(update).start()
}

