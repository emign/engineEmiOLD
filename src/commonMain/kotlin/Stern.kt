package engineEmi.CanvasElements

import com.soywiz.korim.color.RGBA
import engine

class Stern(radius: Number, fuellFarbe: RGBA, randFarbe: RGBA) :
    Kreis(radius = radius, fuellFarbe = fuellFarbe, randFarbe = randFarbe) {
    override suspend fun animate() {
        super.animate()
        x = engine.view.width / 2.0
        y = engine.view.height / 2.0

    }
}