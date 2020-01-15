package engineEmi.ScreenElements.CanvasElements

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice
import com.soywiz.korim.bitmap.sliceWithSize
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.delay
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Verwaltet die Sprites für die Animation. Input ist eine SpriteMap (in der Regel PNG oder JPG), welche alle Phasen
 * der Animation enthält. Die einzelnen Sprites werden dann anhand der als Parameter übergebenen
 * Koordinaten ausgelesen.
 * @property x x-Koordinate
 * @property y y-Koordinate
 * @property spriteWidth Breite des Einzelsprites in der Spritemap in Pixeln
 * @property spriteHeight Höhe des Einzelsprites in der Spritemap in Pixeln
 * @property marginTop Abstand des ersten Sprites von der oberen Grenze der Spritemap
 * @property marginLeft Abstand des ersten Sprites von der linken Grenze der Spritemap
 * @property columns Anzahl der Sprites in einer Zeile (von links nach rechts)
 * @property lines Anzahl der Sprites in einer Reihe (von oben nach unten)
 * @property offsetBetweenColumns Abstand zwischen den Spalten in Pixeln
 * @property offsetBetweenLines Abstand zwischen den Zeilen in Pixeln
 * @property skalierung Skalierung
 * @property bildDatei Der String zur Bilddatei in Resources-Ordner
 * @property bitmap Alternativ zur bildDatei kann auch direkt ein Bitmap übergeben werden. Dies ist
 * insbesondere dann sinnvoll, wenn mehrere Animationen die gleiche Spritemap verwenden, um Ressourcen zu schonen
 * @property spriteView Der View des Sprites. Muss vor der Initialisierung manuell erstellt werden
 * @constructor
 */
class SpriteAnimation(
    var x: Number = 100.0,
    var y: Number = 100.0,
    var spriteWidth: Int = 16,
    var spriteHeight: Int = 16,
    var marginTop: Int = 0,
    var marginLeft: Int = 0,
    var columns: Int = 1,
    var lines: Int = 1,
    var offsetBetweenColumns: Int = 0,
    var offsetBetweenLines: Int = 0,
    var skalierung: Double = 1.0,
    var bildDatei: String = "",
    val bitmap: Bitmap? = null,
    var spriteView: SpriteView
) {

    private val defaultSprite = Bitmaps.transparent
    private var sprites: MutableList<BmpSlice> = mutableListOf(defaultSprite)
    private var currentSpriteIndex = 0
    private val currentSprite: BmpSlice
        get() = sprites[currentSpriteIndex]

    init {
        CoroutineScope(Dispatchers.Default).launch {
            prepareElement()
        }
        updateSpriteView()
    }

    private fun updateSpriteView() {
        spriteView.apply {
            x = x
            y = y
            refreshViewWithSprite(currentSprite, skalierung)
        }
    }

    private suspend fun prepareElement() {
        var line = 0
        repeat(columns) { spalte ->
            val resourceBitmap: Bitmap = if (bitmap is Bitmap) {
                bitmap
            } else {
                resourcesVfs[bildDatei].readBitmap()
            }
            addSpriteToList(
                resourceBitmap.sliceWithSize(
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
        updateSpriteView()
    }

    private fun addSpriteToList(sprite: BmpSlice) {
        sprites.add(sprite)
        if (sprites.first() == defaultSprite) {
            sprites.removeAt(0)
        }
    }

    private fun nextSprite() {
        currentSpriteIndex = (currentSpriteIndex + 1) % sprites.size
        updateSpriteView()
    }

    private fun previousSprite() {
        currentSpriteIndex = (currentSpriteIndex - 1) % sprites.size
        updateSpriteView()
    }

    /**
     * Spielt die Animation ab.
     * @param timeBetweenSprites TimeSpan
     */
    fun play(timeBetweenSprites: TimeSpan = 25.milliseconds) {
        CoroutineScope(Dispatchers.Default).launch {
            nextSprite()
            delay(timeBetweenSprites)
        }
    }
}
