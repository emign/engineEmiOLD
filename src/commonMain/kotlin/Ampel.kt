import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korim.color.Colors
import engineEmi.Controller
import engineEmi.ScreenElements.CanvasElements.Rechteck

object Ampel : Controller {
    val gehaeuse = Gehaeuse(100, 300)
    val lampeRot = Lampe(Colors.RED, gehaeuse)
    val lampeGelb = Lampe(Colors.YELLOW, gehaeuse)
    val lampeGruen = Lampe(Colors.GREEN, gehaeuse)

    var phasen = arrayOf(Phase.AUS, Phase.ROT, Phase.ROTGELB, Phase.GRUEN, Phase.GELB)
    var aktuellePhase = 0

    init {
        engine.register(gehaeuse)
        engine.register(lampeRot)
        engine.register(lampeGelb)
        engine.register(lampeGruen)
    }

    override fun reactToKeyEvent(event: KeyEvent) {
        if (event.key == Key.SPACE && event.type == KeyEvent.Type.DOWN) {
            naechstePhase()
            lampenSchalten(phasen[aktuellePhase])
        }
    }

    fun naechstePhase() {
        val neuePhase = (aktuellePhase + 1) % phasen.size
        aktuellePhase = if (neuePhase == 0) 1 else neuePhase
    }

    fun lampenSchalten(phase: Phase) {
        when (phase) {
            Phase.AUS -> {
                lampeRot.aus(); lampeGelb.aus(); lampeGruen.aus()
            }
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

    fun istGruen() = phasen[aktuellePhase] == Phase.GRUEN

}

class Gehaeuse(breite: Int, hoehe: Int) :
    Rechteck(x = 50, y = 50, breite = breite, hoehe = hoehe, fuellFarbe = Colors.DARKGRAY)