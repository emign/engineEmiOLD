package engineEmi.CanvasElements

import com.soywiz.korim.color.RGBA
import engine
import kotlin.math.cos
import kotlin.math.sin

abstract class Planet(radius: Int, var umlaufbahn: umlaufbahn, farbe: RGBA, farbeHuelle: RGBA, dickeHuelle: Int) :
    Kreis(fuellFarbe = farbe, randFarbe = farbeHuelle, randDicke = dickeHuelle, radius = radius) {

    override suspend fun animate() {
        super.animate()
        val mittelPunktSonnenSystemHorizontal = engine.view.width / 2.0
        val mittelPunktSonnenSystemVertikal = engine.view.height / 2.0
        x = mittelPunktSonnenSystemHorizontal + cos(umlaufbahn.winkel) * umlaufbahn.radius.toDouble()
        y = mittelPunktSonnenSystemVertikal + sin(umlaufbahn.winkel) * umlaufbahn.radius.toDouble() / 2
        umlaufbahn.winkel = umlaufbahn.winkel + umlaufbahn.umlaufGeschwindigkeit
    }


}


