package GUI


import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Polygon, Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object NewGame extends JFXApp{
  var sceneGraphics: Group = new Group {}
  var playerX: Double = 600.0
  var playerY: Double = 600.0
  var playerSpeed: Double = 10
  var rotate: Double = 45
  var curX: Double = 0.0
  var curY: Double = 0.0
  var potX: Double = 0.0
  var potY: Double = 0.0


 val player = Polygon (playerX, playerY, playerX - 40,playerY +80,playerX + 40,playerY + 80)

 sceneGraphics.children.add(player)

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "D" => rotateR()
    //  case "D" => player.rotate.value -= 10
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


  def rotateR(): Unit = {


    }



  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(800, 800) {
      content = List(sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
      //addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed2(event.getCode))
      //addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed3(event.getCode))

    }
  }


  val update: Long => Unit = (time: Long) => {


  }


  // Start Animations. Calls update 60 times per second (takes update as an argument)
  AnimationTimer(update).start()
}
