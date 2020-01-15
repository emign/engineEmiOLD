package engineEmi.ScreenElements.CanvasElements

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice
import engineEmi.Controller
import engineEmi.Log

class AnimationController : Controller {
    var animations = mutableMapOf<String, SpriteAnimation>()


    fun addAnimation(name: String = "main", animation: SpriteAnimation) {
        animations.put(name, animation)
    }

    fun play(name: String, timeBetweenSprites: TimeSpan = 25.milliseconds) {
        if (animations[name] == null) {
            Log.log("Animation $name nicht gefunden")
        } else {
            animations[name]?.play(timeBetweenSprites)
        }
    }

    override fun reactToKeyEvent(event: KeyEvent) {
        when (event.key) {
            Key.LEFT -> play("left")
            Key.RIGHT -> play("right")
            Key.DOWN -> play("down")
            Key.UP -> play("up")
            Key.SPACE -> play("jump")
        }
    }
}

class AnimationControllerSprite(
    x: Int = 0,
    y: Int = 0,
    var skalierung: Float = 1.0f
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {

    var sprite: BmpSlice = Bitmaps.transparent
        set(value) {
            field = value
            updateGraphics()
        }

    override fun updateGraphics() {
        removeChildren()
        image(sprite) {
            position(x, y)
        }.scale(skalierung)
    }

    override suspend fun prepareElement() {

    }
}