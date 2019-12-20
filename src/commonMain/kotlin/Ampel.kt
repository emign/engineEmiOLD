import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korim.color.Colors
import engineEmi.Controller
import engineEmi.Phase
import engineEmi.ScreenElements.CanvasElements.Rechteck

class Ampel : Controller {
    val gehaeuse = Gehaeuse(100, 300)
    val lampeRot = Lampe(Colors.RED, gehaeuse)
    val lampeGelb = Lampe(Colors.YELLOW, gehaeuse)
    val lampeGruen = Lampe(Colors.GREEN, gehaeuse)

    var phasen = arrayOf(Phase.ROT, Phase.ROTGELB, Phase.GRUEN, Phase.GELB)
    var aktuellePhase = 0

    init {
        engine.register(gehaeuse)
        engine.register(lampeRot)
        engine.register(lampeGelb)
        engine.register(lampeGruen)
    }

    override fun reactToKeyEvent(event: KeyEvent) {
        if (event.key == Key.SPACE && event.type == KeyEvent.Type.DOWN) {
            lampenSchalten(phasen[aktuellePhase])
            naechstePhase()
        }
    }

    fun naechstePhase() {
        aktuellePhase = (aktuellePhase + 1) % phasen.size
    }

    fun lampenSchalten(phase: Phase) {
        when (phase) {
            Phase.ROT -> {
                lampeRot.an(); lampeGelb.aus(); lampeGruen.aus()
            }
            Phase.ROTGELB -> {
                lampeRot.an(); lampeGelb.an(); lampeGruen.aus()
            }
            Phase.GRUEN -> {
                lampeRot.aus(); lampeGelb.aus(); lampeGruen.an()
            }
            Phase.GELB -> {
                lampeRot.aus(); lampeGelb.an(); lampeGruen.aus()
            }
        }
    }
}

class Gehaeuse(breite: Int, hoehe: Int) :
    Rechteck(x = 50, y = 50, breite = breite, hoehe = hoehe, fuellFarbe = Colors.DARKGRAY)