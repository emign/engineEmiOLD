package UebungArraySortieren

import com.soywiz.korim.color.RGBA
import engine
import engineEmi.ScreenElements.CanvasElements.Rechteck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ArrayController {

    lateinit var array: Array<Rechteck>
    var sortieralgorithmus: Sortieralgorithmus = KotlinSortierer
    private val breite = 5
    private val startX = 10
    private val startY = 200
    private val abstand = 0
    private val randomSubColor: Int
        get() = (0..255).random()

    fun arrayErzeugen(laenge: Int, untereZufallsGrenze: Int = 10, obereZufallsGrenze: Int = 100) {
        array = Array<Rechteck>(laenge) { i ->
            Rechteck(
                x = startX + (i * (breite + abstand)),
                y = startY,
                fuellFarbe = RGBA(randomSubColor, randomSubColor, randomSubColor),
                breite = breite,
                hoehe = (untereZufallsGrenze..obereZufallsGrenze).random()
            ).apply { this.rotationDegrees = 180.0 }
        }
        engine.register(array)
    }

    fun sortieren() {
        CoroutineScope(Dispatchers.Default).launch {
            sortieralgorithmus.sortieren(array)
            positionenAktualisieren()
        }
    }

    fun positionenAktualisieren() {
        array.forEachIndexed { index, rechteck ->
            rechteck.x = startX + (index * (breite + abstand)).toDouble()
        }

    }
}

fun <T> Array<T>.tauscheIndexPositionen(l: Int, r: Int) {
    val tmp = this[l]
    this[l] = this[r]
    this[r] = tmp
}