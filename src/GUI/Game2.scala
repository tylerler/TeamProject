package GUI

import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}


object Game2 extends JFXApp {
  val windowWidth: Double = 800
  val windowHeight: Double = 600

  val playerCircleRadius:Double = 20

  val rectangleWidth: Double = 60
  val rectangleHeight: Double = 40
  val playerSpeed: Double = 10
  val playerSpeed2: Double = 50

  var allRectangles: List[Shape] = List()

  var sceneGraphics: Group = new Group {}

  val player: Rectangle = new Rectangle() {
    width = 60.0
    height = 40.0
    translateX = 60.0
    translateY = 10.0
    fill = Color.Blue
  }
  sceneGraphics.children.add(player)

  def Bombs(centerX: Double, centerY: Double): Unit = {
    val newRectangle = new Rectangle() {
      width = rectangleWidth
      height = rectangleHeight
      translateX = centerX - rectangleWidth / 2.0
      translateY = centerY - rectangleHeight / 2.0
      fill = Color.Blue
    }
    sceneGraphics.children.add(newRectangle)
    allRectangles = newRectangle :: allRectangles
  }

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
      addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => Bombs(event.getX, event.getY))


    }
  }
  val update: Long => Unit = (time: Long) => {
    for (shape <- allRectangles) {
      shape.rotate.value += 0.9
    }
  }


  // Start Animations. Calls update 60 times per second (takes update as an argument)
  AnimationTimer(update).start()
}

