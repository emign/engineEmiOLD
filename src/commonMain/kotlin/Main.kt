import com.soywiz.korev.Key
import com.soywiz.korev.MouseButton
import engineEmi.Bodies.Ebody
import engineEmi.CanvasElements.CanvasElement
import engineEmi.Engine
import engineEmi.Input.Input
import engineEmi.Input.KeyboardAction
import engineEmi.Input.MouseAction

/**
 * Das Default (und eigentlich immer) das einzige Engine-Objekt
 */
val engine = Engine()

/**
 * Startpunkt für alle Programme.
 * Hier werden [Ebody] und [CanvasElement] Objekte bei der [Engine] registriert.
 * Hierzu kann man die Methoden [Engine.registerCanvasElement], [Engine.registerBody] und [Engine.register] verwenden.
 * Wir der Parameter sample auf true gesetzt, wird eine Funktionsdemo automatisch geladen
 */
fun main() {
    engine.run(input = Input.KEYBOARD) {

        val kreis = BeweglicherKreis()
        val links = KeyboardAction(key = Key.LEFT, target = kreis)
        val rechts = KeyboardAction(key = Key.RIGHT, target = kreis)
        val runter = KeyboardAction(key = Key.DOWN, target = kreis)
        val hoch = KeyboardAction(key = Key.UP, target = kreis)
        val mouseBewegung = MouseAction(button = MouseButton.LEFT, target = kreis)

        engine.registerInput(links)
        engine.registerInput(rechts)
        engine.registerInput(runter)
        engine.registerInput(hoch)

        engine.registerCanvasElement(kreis)
        // HIER WIRD PROGRAMMIERT

    }
}

