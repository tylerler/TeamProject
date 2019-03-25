package GUI


import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}

object NewGame extends JFXApp{
  var sceneGraphics: Group = new Group {}
  var playerX: Double = 600.0
  var playerY: Double = 600.0
  var playerSpeed: Double = 10
  var rotate: Double = 45
  var windowWidth = 800
  var windowHeight = 800
  var potX: Double = 0.0
  var potY: Double = 0.0
  var bullX: Double = 0
  var bullY: Double = 0


  var count: Int = 0
  var arr: Array[Array[Int]] = Array(Array(0,-1), Array(-1,-1),Array(-1,0),Array(-1,1),Array(0,1),Array(1,1),Array(1,0),Array(1,-1))
  var allBullets: List[Shape] = List()


  val player = Polygon (playerX, playerY, playerX - 40,playerY +80,playerX + 40,playerY + 80)

  sceneGraphics.children.add(player)

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "A" => rotateL()
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }





  def bullets(): Unit = {
    val bullet = new Circle() {
      centerX = 200
      centerY = 200
      radius = 20
    }
    bullX = arr(count)(0)
    bullY = arr(count)(1)


    sceneGraphics.children.add(bullet)
    allBullets = bullet :: allBullets
  }

  def rotateL(): Unit = {
    count += 1
    if(count == 8){
      count = 0
    }
    potX = arr(count)(0)
    potY = arr(count)(1)
    player.rotate.value -= 45

  }



  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(windowWidth, windowWidth) {
      content = List(sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
      //addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed2(event.getCode))

    }
  }


  val update: Long => Unit = (time: Long) => {
    player.translateX.value += potX
    player.translateY.value += potY

    for(bullet <- allBullets){
      bullet.translateX.value+= 15
      bullet.translateY.value += 15
    }


  }


  // Start Animations. Calls update 60 times per second (takes update as an argument)
  AnimationTimer(update).start()
}
