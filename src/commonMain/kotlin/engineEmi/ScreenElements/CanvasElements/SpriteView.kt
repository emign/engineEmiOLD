package engineEmi.ScreenElements.CanvasElements

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice
import engineEmi.Controller

class SpriteView(
    x: Number = 100.0,
    y: Number = 100.0,
    var skalierung: Float = 1.0f,
    var initialSprite: BmpSlice? = null
) : Controller, CanvasElement(x = x.toDouble(), y = y.toDouble()) {

    var sprite: BmpSlice = initialSprite ?: Bitmaps.transparent
        set(value) {
            field = value
            updateGraphics()
        }

    init {
        updateGraphics()
    }

    override suspend fun prepareElement() {
        super.prepareElement()
    }

    fun refreshViewWithSprite(sprite: BmpSlice, skalierung: Float) {
        this.sprite = sprite
        this.skalierung = skalierung
    }

    override fun updateGraphics() {
        removeChildren()
        image(sprite) {
            position(x, y)
        }.scale(skalierung)
    }

    override fun reactToKeyEvent(event: KeyEvent) {
        when (event.key) {
            Key.LEFT -> x -= 10
            Key.RIGHT -> x += 10
            Key.DOWN -> y += 10
            Key.UP -> y -= 10

        }
    }
}