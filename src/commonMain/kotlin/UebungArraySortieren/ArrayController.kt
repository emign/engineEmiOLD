package UebungArraySortieren

import com.soywiz.korim.color.RGBA
import engineEmi.Engine
import engineEmi.ScreenElements.CanvasElements.Rechteck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ArrayController {

    lateinit var array: Array<Rechteck>
    var sortieralgorithmus: Sortieralgorithmus = KotlinSortierer
    val breite = 5
    val startX = 10
    val startY = 200
    val abstand = 0
    val randomSubColor: Int
        get() = (0..255).random()
    lateinit var engine: Engine

    fun engineSetzen(engine: Engine) {
        this.engine = engine
    }

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
            println(sortieralgorithmus)
            sortieralgorithmus.sortieren(array)
            println(array.map { it.hoehe }.toList())
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