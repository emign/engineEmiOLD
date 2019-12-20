import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korim.color.Colors
import engineEmi.Controller
import engineEmi.ScreenElements.CanvasElements.Rechteck

class Ampel : Controller {
    val gehaeuse = Gehaeuse(100, 300)
    val lampeRot = Lampe(Colors.RED, gehaeuse)
    val lampeGelb = Lampe(Colors.YELLOW, gehaeuse)
    val lampeGruen = Lampe(Colors.GREEN, gehaeuse)

    init {
        engine.register(gehaeuse)
        engine.register(lampeRot)
        engine.register(lampeGelb)
        engine.register(lampeGruen)
    }

    override fun reactToKeyEvent(event: KeyEvent) {
        if (event.key == Key.SPACE) {
            lampeRot.aus()
        }
    }
}

class Gehaeuse(breite: Int, hoehe: Int) :
    Rechteck(x = 50, y = 50, breite = breite, hoehe = hoehe, fuellFarbe = Colors.DARKGRAY)