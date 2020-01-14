package engineEmi.ScreenElements.CanvasElements

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import engineEmi.Log

class AnimationController(val bildDatei: String) {
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
}