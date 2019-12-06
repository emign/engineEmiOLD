package engineEmi

import com.soywiz.klock.milliseconds
import com.soywiz.korev.keys
import com.soywiz.korev.mouse
import com.soywiz.korge.Korge
import com.soywiz.korge.box2d.WorldView
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.input.*
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launch
import engineEmi.Bodies.Ebody
import engineEmi.CanvasElements.CanvasElement
import engineEmi.Input.Input
import engineEmi.Input.InputAction
import engineEmi.Samples.HugeSample.HugeSample
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
    var inputActions = mutableListOf<InputAction>()
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
        sample: Boolean = false,
        input: Input = Input.NONE,
        inputs: List<Input> = emptyList<Input>(),
        body: suspend () -> Unit
    ) {
        this.input = listOf(input).plus(inputs)
        var nbody = body
        if (sample) {
            nbody = HugeSample(this).invoke()
        }
        GlobalScope.launch {
            main(nbody)
        }

    }

    suspend fun main(body: suspend () -> Unit) = Korge(quality = GameWindow.Quality.PERFORMANCE, title = "Engine Emi") {
        body()
        view.width = this.views.virtualWidth
        view.height = this.views.virtualHeight
        views.clearColor = Colors.WHITE
        // Physik
        worldView {
            position(400, 400).scale(10)
            // X: -20 bis +50
            // Y: -20 bis +20

            if (!bodies.isEmpty()) {

                bodies.run {
                    map { registerBodyWithWorld(it) }
                    map { it.body }
                }
            }


        }

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

        // INPUT
        keys {
            onKeyDown { inputActions.onEach { keyboardAction -> keyboardAction.sendEvent(it) } }
            onKeyUp { inputActions.onEach { keyboardAction -> keyboardAction.sendEvent(it) } }

        }

        mouse {
            onDown { inputActions.onEach { mouseAction -> { println("mouse");mouseAction.sendEvent(it) } } }
            onUp { inputActions.onEach { mouseAction -> mouseAction.sendEvent(it) } }
            onClick { inputActions.onEach { mouseAction -> { println("mouse");mouseAction.sendEvent(it) } } }
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

    fun registerInput(action: InputAction) {
        inputActions.add(action)
    }

    fun registerInputs(actions: Collection<InputAction>) {
        actions.forEach { registerInput(it) }
    }


}

class ViewWindow {
    var height = 0
    var width = 0
}
