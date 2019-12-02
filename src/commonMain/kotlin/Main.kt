import engineEmi.Bodies.Ebody
import engineEmi.CanvasElements.CanvasElement
import engineEmi.Engine

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
suspend fun main() {
    engine.run(sample = true) {

        // HIER WIRD PROGRAMMIERT

    }
}

