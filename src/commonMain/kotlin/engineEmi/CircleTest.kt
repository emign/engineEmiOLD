package engineEmi

import com.soywiz.korim.color.Colors
import engineEmi.Bodies.Circle
import org.jbox2d.dynamics.BodyType

class CircleTest : Circle(x = 20, y = 20, radius = 5, fillColor = Colors.GREEN, bodyType = BodyType.KINEMATIC) {
}