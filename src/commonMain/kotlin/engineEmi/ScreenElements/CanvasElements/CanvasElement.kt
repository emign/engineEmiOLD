package engineEmi.ScreenElements.CanvasElements


import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sgraphics
import engineEmi.Log
import engineEmi.ScreenElements.ScreenElement


abstract class CanvasElement(
    x: Double,
    y: Double
) : Container(), ScreenElement {

    init {
        super.x = x
        super.y = y
    }

    /**
     * Autoskalierender Vektor->Bitmap Wandler
     */
    val graphics = sgraphics {
    }

    /**
     * Hier werden die Animationsbefehle gespeichert.
     */
    var animationRoutine: () -> Any = {}

    /**
     * Muss in Subklassen überschrieben werden, falls man das Objekt animieren will.
     * Wird im default ca. 60 Mal pro Sekunde aufgerufen.
     * Änderungen der Parameter wie etwa x und y werden so direkt angezeigt, wenn man sie überschreibt.
     * Alternativ kann man auch bestehenden Objekten neue Animationen zuweisen. Die geht etwa mit Hilfe von [animate]
     */
    open suspend fun onEveryFrame() {
        animationRoutine()
    }

    open suspend fun animate() {
        Log.log("Überholt. Bitte onEveryFrame() verwedenn")
        onEveryFrame()
    }

    /**
     * Bereite das Element vor (wird in Subklassen überschrieben).
     * Siehe Implementierung von [Kreis] oder [Rechteck] für Beispiele
     */
    open suspend fun prepareElement() {}

    /**
     * Zeichnet das Objekt. Siehe Implementierung von [Kreis] oder [Rechteck] für Beispiele
     */
    abstract fun updateGraphics()


}
