package engineEmi.ScreenElements.CanvasElements

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import engineEmi.Controller
import engineEmi.Log

/**
 * Verwaltete mehrere Animationen. Etwa die Links-, Rechts-, Oben-, Unten- Laufanimationen eines Charakters
 * @property animations Beinhaltete die Animationen
 */
class AnimationController : Controller {
    private var animations = mutableMapOf<String, SpriteAnimation>()

    /**
     * Fügt eine Animation unter einem bestimmten Namen hinzu
     * @param name Name der Animation
     * @param animation Die Animation
     */
    fun addAnimation(name: String = "main", animation: SpriteAnimation) {
        animations.put(name, animation)
    }

    /**
     * Spielt eine vorher mit [AnimationController.addAnimation] hinzugefügte ab
     * @param name Name der Animation
     * @param timeBetweenSprites Wartezeit zwischen den Animationsschritten
     */
    fun play(name: String, timeBetweenSprites: TimeSpan = 25.milliseconds) {
        if (animations[name] == null) {
            Log.log("Animation $name nicht gefunden")
        } else {
            animations[name]?.play(timeBetweenSprites)
        }
    }
}