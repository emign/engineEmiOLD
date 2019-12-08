import com.soywiz.korev.Key
import com.soywiz.korev.MouseButton
import com.soywiz.korev.MouseEvent
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import engineEmi.CanvasElements.Kreis
import engineEmi.Input.Keyboard


class BeweglicherKreis(radius: Int = 50, x: Int = 100, y: Int = 100, fuellFarbe: RGBA = Colors.ALICEBLUE) :
    Kreis(radius = radius, x = x, y = y, fuellFarbe = fuellFarbe) {


    override suspend fun animate() {
        if (Keyboard.isKeyDown(Key.DOWN))
            y++
        if (Keyboard.isKeyDown(Key.UP))
            y--
        if (Keyboard.isKeyDown(Key.LEFT))
            x--
        if (Keyboard.isKeyDown(Key.RIGHT))
            x++
        if (Keyboard.isKeyDown(Key.SPACE))
            radius = radius++
        if (Keyboard.isKeyDown(Key.X))
            radius = radius++


    }

    override fun reactToMouseEvent(event: MouseEvent) {
        if (event.type == MouseEvent.Type.DRAG) {
            if (event.button == MouseButton.BUTTON3) {
                radius++
            }
            if (event.button == MouseButton.RIGHT) {
                radius--
            }

        }




        if (event.type == MouseEvent.Type.MOVE) {
            x = event.x.toDouble()
            y = event.y.toDouble()
        }

    }
}


