package engineEmi.ScreenElements.CanvasElements

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice
import com.soywiz.korim.bitmap.sliceWithSize
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.delay
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpriteAnimation(
    x: Number = 100.0,
    y: Number = 100.0,
    var spriteWidth: Int = 16,
    var spriteHeight: Int = 16,
    var marginTop: Int = 0,
    var marginLeft: Int = 0,
    var columns: Int = 1,
    var lines: Int = 1,
    var offsetBetweenColumns: Int = 0,
    var offsetBetweenLines: Int = 0,
    var skalierung: Float = 1.0f,
    var bildDatei: String
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {

    val defaultSprite = Bitmaps.transparent
    private var sprites: MutableList<BmpSlice> = mutableListOf(defaultSprite)
    var currentSpriteIndex = 0
    val currentSprite: BmpSlice
        get() = sprites[currentSpriteIndex]
    var subAnimations = mutableMapOf("main" to this)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            prepareElement()
        }
        updateGraphics()
    }

    override fun updateGraphics() {
        removeChildren()
        image(currentSprite) {
            position(x, y)
        }.scale(skalierung)
    }

    override suspend fun prepareElement() {
        var line = 0
        repeat(columns) { spalte ->
            addSpriteToList(
                resourcesVfs[bildDatei].readBitmap().sliceWithSize(
                    marginLeft + (spriteWidth + offsetBetweenColumns) * spalte,
                    marginTop + (spriteHeight + offsetBetweenLines) * line,
                    spriteWidth,
                    spriteHeight
                )
            )
            if (spalte % columns == 0 && spalte != 0) {
                line++
            }
        }
        updateGraphics()
    }

    private fun addSpriteToList(sprite: BmpSlice) {
        sprites.add(sprite)
        if (sprites.first() == defaultSprite) {
            sprites.removeAt(0)
        }
    }

    override suspend fun onEveryFrame() {
        updateGraphics()
    }

    private fun nextSprite() {
        currentSpriteIndex = (currentSpriteIndex + 1) % sprites.size
    }

    private fun previousSprite() {
        currentSpriteIndex = (currentSpriteIndex - 1) % sprites.size
    }

    fun play(timeBetweenSprites: TimeSpan = 25.milliseconds) {
        CoroutineScope(Dispatchers.Default).launch {
            nextSprite()
            delay(timeBetweenSprites)
        }
    }


}
