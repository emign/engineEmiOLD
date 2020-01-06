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
    }

    fun aus() {
    }

    private fun anzeigeAktualisieren() {
    }

    fun schalten() {

    }


}