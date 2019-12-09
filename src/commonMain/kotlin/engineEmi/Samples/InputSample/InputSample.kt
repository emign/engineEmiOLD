package engineEmi.Samples.InputSample

import com.soywiz.korev.Key
import com.soywiz.korev.MouseEvent
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import engineEmi.CanvasElements.Kreis
import engineEmi.Engine
import engineEmi.Input.Keyboard

object InputSample {
    fun invoke(engine: Engine): suspend () -> Unit = {
        engine.registerCanvasElement(BeweglicherKreis(x = 200, y = 300, fuellFarbe = Colors.GREEN, radius = 30))
    }
}

class BeweglicherKreis(
    x: Number,
    y: Number,
    fuellFarbe: RGBA,
    radius: Number
) : Kreis(x = x, y = y, fuellFarbe = fuellFarbe, radius = radius) {
    override fun reactToMouseEvent(event: MouseEvent) {
        this.x = event.x.toDouble()
        this.y = event.y.toDouble()
    }

    override suspend fun animate() {
        super.animate()
        if (Keyboard.isKeyDown(Key.DOWN)) {
            radius--
        }
        if (Keyboard.isKeyDown(Key.UP)) {
            radius++
        }
    }
}
