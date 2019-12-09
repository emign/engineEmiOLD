import engineEmi.Audio.Audio
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
 * Beispiele kann man laden, indem man das Sample als Parameter in [Engine.run] übergibt
 * Zum Beispiel: engine.run(sample = Samples.INPUT)
 */
fun main() {
    engine.run {

        // HIER WIRD PROGRAMMIERT
        val playMusic = Audio("audio/music/Jahzzar_-_05_-_Siesta.mp3").play()

    }
}

