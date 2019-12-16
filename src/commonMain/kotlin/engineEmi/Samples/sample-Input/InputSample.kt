import com.soywiz.korev.Key
import com.soywiz.korev.MouseEvent
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.runBlockingNoSuspensions
import engineEmi.Controller
import engineEmi.Engine
import engineEmi.Input.Keyboard
import engineEmi.ScreenElements.Bodies.Ebody
import engineEmi.ScreenElements.CanvasElements.CanvasElement
import engineEmi.ScreenElements.CanvasElements.Kreis


/**
 * Das Default (und eigentlich immer) das einzige Engine-Objekt
 */
object InputSample {
    val engine = Engine()

    /**
     * Startpunkt für alle Programme.
     * Hier werden u.a. [Ebody] und [CanvasElement] Objekte bei der [Engine] registriert.
     * Es gibt drei Bereiche:
     * init : Dieser Code-Block wird zur Konfiguration der Engine verwendet. Hier kann man z.B. die Höhe und Breite des Fensters festlegen
     * viewWillLoad: Dieser Code-Block wird NACH der Konfiguration aber VOR dem Aufbau des Views (der Anzeige) ausgeführt. Hier sollte man
     * seine Objekte bei der Engine registrieren
     * viewDidLoad: Dieser Code-Block wird NACH dem der View komplett aufgebaut wurde ausgeführt. Hier sollte man Code platzieren, der darauf
     * angewiesen ist, dass Objekte bereits fertig erstellt und registriert wurden. Dies trifft vor allem auf [Ebody] Objekte zu.
     */
    fun main() = runBlockingNoSuspensions {
        engine.run {

            /**
             * Code um die Engine zu konfigurieren
             */
            init {
            }

            /**
             * Code der VOR dem Aufbau des Views ausgeführt wird
             */
            viewWillLoad {
                registerCanvasElement(BeweglicherKreis(x = 200, y = 300, fuellFarbe = Colors.GREEN, radius = 30))
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

class BeweglicherKreis(
    x: Number,
    y: Number,
    fuellFarbe: RGBA,
    radius: Number
) : Kreis(x = x, y = y, fuellFarbe = fuellFarbe, radius = radius), Controller {
    override fun reactToMouseEvent(event: MouseEvent) {
        this.x = event.x.toDouble()
        this.y = event.y.toDouble()
    }

    override suspend fun animate() {
        super.animate()
        if (Keyboard.isKeyDown(Key.DOWN)) {
            radius--
        }
        if (Keyboard.isKeyDown(Key.UP)) {
            radius++
        }
    }
}


