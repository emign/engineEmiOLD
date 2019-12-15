import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs
import engineEmi.Engine
import engineEmi.ScreenElements.Bodies.Image
import engineEmi.ScreenElements.Bodies.Rectangle
import kotlinx.coroutines.GlobalScope
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.BodyType

object PhysikSample {
    val engine = Engine()

    val bird = Image(
        x = -30,
        y = 50,
        bodyType = BodyType.DYNAMIC,
        imageFile = "gfx/bird.png",
        angle = 1F,
        friction = 1f,
        density = 10f
    )

    fun main() {
        GlobalScope.launch {
            engine.run {

                /**
                 * Code um die Engine zu konfigurieren.
                 */
                init {
                }

                /**
                 * Code der VOR dem Aufbau des Views ausgeführt wird
                 */
                viewWillLoad {
                    val boden =
                        Rectangle(
                            x = -0,
                            y = -30,
                            bodyType = BodyType.STATIC,
                            height = 1,
                            width = 150,
                            fillColor = Colors.DARKGRAY
                        )
                    val rechts =
                        Rectangle(
                            x = 50,
                            y = 10,
                            bodyType = BodyType.STATIC,
                            height = 150,
                            width = 1,
                            fillColor = Colors.DARKGRAY
                        )


                    engine.registerBody(bird)
                    engine.registerBody(boden)
                    engine.registerBody(rechts)


                    val kistenBild = resourcesVfs["gfx/crate.png"].readBitmap()
                    engine.registerBody(Kiste(25, -28, kistenBild))
                    engine.registerBody(Kiste(31, -28, kistenBild))
                    engine.registerBody(Kiste(37, -28, kistenBild))
                    engine.registerBody(Kiste(43, -28, kistenBild))
                    engine.registerBody(Kiste(49, -28, kistenBild))
                    engine.registerBody(Kiste(34, -22, kistenBild))
                    engine.registerBody(Kiste(28, -22, kistenBild))
                    engine.registerBody(Kiste(40, -22, kistenBild))
                    engine.registerBody(Kiste(46, -22, kistenBild))
                    engine.registerBody(Kiste(25, -15, kistenBild))
                    engine.registerBody(Kiste(31, -15, kistenBild))
                    engine.registerBody(Kiste(37, -15, kistenBild))
                    engine.registerBody(Kiste(43, -10, kistenBild))
                    engine.registerBody(Kiste(43, -5, kistenBild))
                    engine.registerBody(Kiste(43, -0, kistenBild))
                    engine.registerBody(Kiste(43, 5, kistenBild))
                    engine.registerBody(Kiste(43, 10, kistenBild))
                    engine.registerBody(Kiste(43, 15, kistenBild))
                    engine.registerBody(Kiste(43, 20, kistenBild))
                    engine.registerBody(Kiste(43, 25, kistenBild))
                }

                /**
                 * Code, der NACH dem Aufbau des Views ausgeführt wird
                 */
                viewDidLoad {
                    bird.body.applyForce(Vec2(1f, 1f), bird.body.worldCenter)
                }

                start()
            }
        }
    }
}

class Kiste(x: Int, y: Int, image: Bitmap) :
    Image(x = x, y = y, bodyType = BodyType.DYNAMIC, preInitializedBitmap = image, density = 10f, restitution = 0f)
