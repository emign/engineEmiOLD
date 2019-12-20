import com.soywiz.klock.milliseconds
import com.soywiz.korge.time.delay
import engineEmi.ScreenElements.CanvasElements.Bild
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Auto : Bild(bildDatei = "gfx/auto.png", skalierung = 0.5f, x = engine.view.width) {

    private var richtung = -1

    init {
        y = engine.view.height - 144.0 * skalierung
    }

    fun fahren() {
        GlobalScope.launch {
            while (true) {
                if (Ampel.istGruen()) {
                    x += richtung
                    if (x < -width) {
                        richtung = 1
                    }
                    if (x > engine.view.width) {
                        richtung = -1
                    }
                }
                delay(16.milliseconds)
            }
        }
    }

}