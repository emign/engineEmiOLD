import com.soywiz.korev.KeyEvent
import com.soywiz.korim.color.Colors
import engineEmi.Controller

object Ampel : Controller {
    private val gehaeuse = Gehaeuse(100, 300)
    private val lampeRot = Lampe(Colors.RED, gehaeuse)
    private val lampeGelb = Lampe(Colors.YELLOW, gehaeuse)
    private val lampeGruen = Lampe(Colors.GREEN, gehaeuse)

    private var phasen = arrayOf(Phase.AUS, Phase.ROT, Phase.ROTGELB, Phase.GRUEN, Phase.GELB)
    private var aktuellePhase = 0

    init {
        engine.register(gehaeuse)
        engine.register(lampeRot)
        engine.register(lampeGelb)
        engine.register(lampeGruen)
    }

    override fun reactToKeyEvent(event: KeyEvent) {
    }

    private fun naechstePhase() {
        val neuePhase = (aktuellePhase + 1) % phasen.size
        aktuellePhase = if (neuePhase == 0) 1 else neuePhase
    }

    private fun schalten(phase: Phase) {
        // Hinweis:
        // Phase.AUS bedeutet lampeRot.aus(); lampeGelb.aus(); lampeGruen.aus()
    }

    fun istGruen() = false

}
