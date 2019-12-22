import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import engineEmi.ScreenElements.CanvasElements.Kreis

class Lampe(var farbe: RGBA, var inGehauese: Gehaeuse) : Kreis(
    fuellFarbe = farbe,
    x = inGehauese.x + inGehauese.width / 2,
    y = inGehauese.y + inGehauese.breite.toInt() * 0.4 + 10,
    radius = inGehauese.breite.toInt() * 0.4
) {

    private var zustand = Zustand.AUS

    init {
        when (farbe) {
            Colors.YELLOW -> y += inGehauese.hoehe.toInt() / 3
            Colors.GREEN -> y += inGehauese.hoehe.toInt() / 1.5
        }
        anzeigeAktualisieren()
    }

    fun an() {
        zustand = Zustand.AN
        anzeigeAktualisieren()
    }

    fun aus() {
        zustand = Zustand.AUS
        anzeigeAktualisieren()
    }

    private fun anzeigeAktualisieren() {
        alpha = when (zustand) {
            Zustand.AUS -> 0.1
            Zustand.AN -> 1.0
        }
    }

    fun schalten() {
        when (zustand) {
            Zustand.AN -> aus()
            Zustand.AUS -> an()
        }
        anzeigeAktualisieren()
    }


}