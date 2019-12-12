package engineEmi

import com.soywiz.klock.milliseconds
import com.soywiz.korev.MouseEvent
import com.soywiz.korev.addEventListener
import com.soywiz.korev.keys
import com.soywiz.korev.mouse
import com.soywiz.korge.Korge
import com.soywiz.korge.box2d.WorldView
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.input.Input
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.input.onKeyUp
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launch
import engineEmi.Bodies.Ebody
import engineEmi.CanvasElements.CanvasElement
import engineEmi.Input.Keyboard
import engineEmi.Samples.HugeSample
import engineEmi.Samples.InputSample.InputSample
import engineEmi.Samples.PhysikSample.PhysikSample
import engineEmi.Samples.Samples
import kotlinx.coroutines.GlobalScope

/**
 * Die Game-Engine. Sie ist ein Singleton und wird mit [Engine.run] gestartet.
 * @property canvasElements Alle registrieten Objekte vom Typ [CanvasElement]
 * @property bodies Alle registrieten Objekte des Typs [Ebody]
 *
 */
class Engine {
    var canvasElements = mutableListOf<CanvasElement>()
    var bodies = mutableListOf<Ebody>()

    var view = ViewWindow()
    var viewHeight = 0.0
        private set
    var viewWidth = 0.0
        private set
    var sample = false
    var input = emptyList<Input>()

    /**
     * Startet die Engine. Kann Parameter zur Konfiguration übernehmen
     * @param sample Lädt eine Beispielszene
     */

    fun run(
        width: Int = 1280,
        height: Int = 720,
        sample: Samples = Samples.NONE,
        inputs: List<Input> = emptyList<Input>(),
        afterSetup: suspend () -> Unit = {},
        scale: Int = 100,
        body: suspend () -> Unit = {}
    ) {
        var nbody = body
        var afterSetup = afterSetup
        when (sample) {
            Samples.HUGE -> nbody = HugeSample.invoke(this)
            Samples.INPUT -> nbody = InputSample.invoke(this)
            Samples.PHYSIK -> {
                nbody = PhysikSample.invoke(this); afterSetup = PhysikSample.afterSetup(this)
            }
            Samples.NONE -> {
            }
        }
        GlobalScope.launch {
            main(body = nbody, afterSetup = afterSetup, width = width, height = height, scale = scale)
        }

    }

    suspend fun main(body: suspend () -> Unit, afterSetup: suspend () -> Unit, width: Int, height: Int, scale: Int) =
        Korge(quality = GameWindow.Quality.PERFORMANCE, title = "Engine Emi", width = width, height = height) {
            body()
            view.width = this.views.virtualWidth
            view.height = this.views.virtualHeight
            views.clearColor = Colors.WHITE
            // Physik

            worldView {


                position(view.width / 2, view.height / 2).scale(scale)

                // X: -20 bis +50
                // Y: -20 bis +20

                if (!bodies.isEmpty()) {

                    bodies.run {
                        map { registerBodyWithWorld(it) }
                        map { it.body }
                    }
                }
            }
            afterSetup()

        //Physikfreie Zone
        if (!canvasElements.isEmpty()) {
            canvasElements.run {
                map { it.prepareElement() }
                map { addChild(it) }
            }
            launch {
                while (true) {
                    canvasElements.onEach { it.animate() }
                    delay(16.milliseconds)
                }
            }

        }

        addEventListener<MouseEvent> { canvasElements.onEach { element -> element.reactToMouseEvent(it) } }
        addEventListener<MouseEvent> { bodies.onEach { element -> element.reactToMouseEvent(it) } }


        // INPUT
        keys {
            onKeyDown { Keyboard.keyDown(it.key) }
            onKeyUp { Keyboard.keyReleased(it.key) }
        }

        mouse {
            onDown { }
        }


    }


    suspend fun WorldView.registerBodyWithWorld(body: Ebody) {
        body.initForWorld(this.world)
    }

    /**
     * Registriert ein [CanvasElement] bei der Engine (reguläre Objekte)
     * @param canvasElement CanvasElement
     */
    fun registerCanvasElement(canvasElement: CanvasElement) {
        canvasElements.add(canvasElement)
    }

    /**
     * Registriert einen [Ebody] bei der Engine (Physikobjekte)
     * @param body Ebody
     */
    fun registerBody(body: Ebody) {
        bodies.add(body)
    }

    /**
     * Registriert einen [Ebody] oder ein [CanvasElement] bei der Engine
     * Es ist auch möglich Arrays und Collections zu registrieren.
     * [Ebody] und [CanvasElement] dürfen in den Arrays oder Collections nicht gemischt vorkommen
     * @param o Any
     */
    fun register(o: Any) {
        when {
            o is Array<*> -> o.map { it?.let { register(it) } }
            o is Collection<*> -> o.map { it?.let { register(it) } }
            o is Ebody -> registerBody(o)
            o is CanvasElement -> registerCanvasElement(o)
        }
    }


}

class ViewWindow {
    var height = 0
    var width = 0
}