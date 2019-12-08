package engineEmi

import com.soywiz.korev.MouseEvent
import com.soywiz.korim.color.Colors
import engineEmi.Bodies.Circle
import org.jbox2d.dynamics.BodyType

class CircleTest : Circle(x = 20, y = 20, radius = 5, fillColor = Colors.GREEN, bodyType = BodyType.KINEMATIC) {
    override fun reachtToMouseEvent(event: MouseEvent) {
        super.reachtToMouseEvent(event)

        this.body.position.x = event.x.toFloat()
        this.body.position.y = event.y.toFloat()

    }
}