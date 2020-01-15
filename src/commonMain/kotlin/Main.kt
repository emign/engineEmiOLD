import com.soywiz.korev.KeyEvent
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs
import engineEmi.Controller
import engineEmi.Engine
import engineEmi.ScreenElements.Bodies.Ebody
import engineEmi.ScreenElements.CanvasElements.AnimationController
import engineEmi.ScreenElements.CanvasElements.CanvasElement
import engineEmi.ScreenElements.CanvasElements.SpriteAnimation
import engineEmi.ScreenElements.CanvasElements.SpriteView
import kotlinx.coroutines.GlobalScope


/**
 * Das Default (und eigentlich immer) das einzige Engine-Objekt
 */
val engine = Engine()

/**
 * Startpunkt für alle Programme.
 * Hier werden u.a. [Ebody] und [CanvasElement] Objekte bei der [Engine] registriert.
 * Es gibt drei Bereiche:
 * init : Dieser Code-Block wird zur Konfiguration der Engine verwendet. Hier kann man z.B. die Höhe und Breite des Fensters festlegen.
 * Wenn man diesen Block leer lässt, werden Standard-Werte geladen
 * viewWillLoad: Dieser Code-Block wird NACH der Konfiguration aber VOR dem Aufbau des Views (der Anzeige) ausgeführt. Hier sollte man
 * seine Objekte bei der Engine registrieren
 * viewDidLoad: Dieser Code-Block wird NACH dem der View komplett aufgebaut wurde ausgeführt. Hier sollte man Code platzieren, der darauf
 * angewiesen ist, dass Objekte bereits fertig erstellt und registriert wurden. Dies trifft vor allem auf [Ebody] Objekte zu.
 */
fun main() {
    GlobalScope.launch {
        engine.run {

            /**
             * Code um die Engine zu konfigurieren.
             */
            init {

            }

            /**
             * Code der VOR dem Aufbau des Views ausgeführt wird
             */
            viewWillLoad {

                val bitmap = resourcesVfs["gfx/character/character.png"].readBitmap()

                val spriteView = SpriteView()

                val animationDown = SpriteAnimation(
                    x = 200,
                    y = 200,
                    columns = 4,
                    marginTop = 0,
                    marginLeft = 1,
                    lines = 1,
                    spriteHeight = 32,
                    spriteWidth = 16,
                    bitmap = bitmap,
                    skalierung = 3.0f,
                    spriteView = spriteView
                )

                val animationRight = SpriteAnimation(
                    x = 200,
                    y = 200,
                    columns = 4,
                    lines = 1,
                    marginTop = 32,
                    marginLeft = 1,
                    spriteWidth = 16,
                    spriteHeight = 32,
                    bitmap = bitmap,
                    skalierung = 3.0f,
                    spriteView = spriteView
                )

                val animationUp = SpriteAnimation(
                    x = 200,
                    y = 200,
                    columns = 4,
                    lines = 1,
                    marginTop = 64,
                    marginLeft = 1,
                    spriteWidth = 16,
                    spriteHeight = 32,
                    bitmap = bitmap,
                    skalierung = 3.0f,
                    spriteView = spriteView
                )

                val animationLeft = SpriteAnimation(
                    x = 200,
                    y = 200,
                    columns = 4,
                    lines = 1,
                    marginTop = 96,
                    marginLeft = 1,
                    spriteWidth = 16,
                    spriteHeight = 32,
                    bitmap = bitmap,
                    skalierung = 3.0f,
                    spriteView = spriteView
                )


                val characterAnimationController = AnimationController()
                characterAnimationController.addAnimation("down", animationDown)
                characterAnimationController.addAnimation("right", animationRight)
                characterAnimationController.addAnimation("up", animationUp)
                characterAnimationController.addAnimation("left", animationLeft)



                register(spriteView)
                register(characterAnimationController)


                //register(characterAnimationController)


            }

            /**
             * Code, der NACH dem Aufbau des Views ausgeführt wird
             */
            viewDidLoad {

            }

            start()
        }
    }
}

class AnimationsController(val anim: SpriteAnimation) : Controller {

    override fun reactToKeyEvent(event: KeyEvent) {
        anim.play()
        anim.y++
    }
}