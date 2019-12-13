import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launch
import engineEmi.Bodies.Circle
import engineEmi.Bodies.Ebody
import engineEmi.CanvasElements.CanvasElement
import engineEmi.CanvasElements.Kreis
import engineEmi.Engine
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
 * angewiesen ist, dass Objekte bereits fertig erstellt und registriert wurden. Dies trifft vor allem auf [EBody] Objekte zu.
 */
fun main() {
    GlobalScope.launch {
        engine.run {

            /**
             * Code um die Engine zu konfigurieren.
             */
            init {
                view.width = 500
                view.height = 500
                view.scale = 100
            }

            /**
             * Code der VOR dem Aufbau des Views ausgeführt wird
             */
            viewWillLoad {
                val kreis = Kreis(50, 100, 100, Colors.RED)
                val circ = Circle(fillColor = Colors.GREEN, radius = 0.5, x = 0, y = 0)
                registerCanvasElement(kreis)
                register(circ)
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

