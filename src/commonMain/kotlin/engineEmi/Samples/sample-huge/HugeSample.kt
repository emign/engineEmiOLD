import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs
import engineEmi.Bodies.Circle
import engineEmi.Bodies.Image
import engineEmi.Bodies.Rectangle
import engineEmi.CanvasElements.Gerade
import engineEmi.Engine
import kotlinx.coroutines.GlobalScope
import org.jbox2d.dynamics.BodyType

object HugeSample {
    val engine = Engine()

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
                    //val meinRechteck = Rechteck(höhe = 100.0, breite = 100.0, x = 100.0, y = 100.0, fuellFarbe = Colors.LAWNGREEN)
                    //val meineGerade =
                    Gerade(x = 10.0, y = 10.0, toX = 100.0, toY = 180.0, dicke = 3, fuellFarbe = Colors.BLUEVIOLET)
                    //val meinBild = Bild(x = 100.0, y = 150.0, bildDatei = "hut.png", skalierung = 0.5f)

                    // val meinArray = arrayOf(meinRechteck, meineGerade)
                    // Engine.register(meinArray)


                    // Engine.registerCanvasElement(meineGerade)
                    // Engine.registerCanvasElement(meinRechteck)
                    // Engine.registerCanvasElement(meinBild)

                    // Engine.registerCanvasElement(circle)
                    //val k2 = Circle(0.0, 30.0, radius = 20F, density = 0.2F, fillColor = Colors.GREEN, bodyType = BodyType.DYNAMIC)

                    //Engine.registerBody(k)
                    //  val dach = Rectangle(x = -10.0, y = -100.0,width = 10000.0, height = 30.0, fillColor = Colors.AZURE, bodyType = BodyType.STATIC)
                    // val boden = Rectangle(x = -20.0, y = 10.0, width = 200f, height = 50f, fillColor = Colors.RED, bodyType = BodyType.STATIC)
                    //val dach = Rectangle(x = 0.0, y = 70.0, width = 200f, height = 10.0f, fillColor = Colors.BLUE, bodyType = BodyType.DYNAMIC)

                    // val test = Rectangle(x = 400, y = 300, width = 10, height = 10, density = 1f, fillColor = Colors.RED, bodyType = BodyType.DYNAMIC)
                    val boden = Rectangle(
                        x = -0,
                        y = 0,
                        width = 1,
                        height = 1,
                        density = 1f,
                        fillColor = Colors.BLUE,
                        bodyType = BodyType.KINEMATIC
                    )
                    val boden2 = Rectangle(
                        x = 3,
                        y = 0,
                        width = 1,
                        height = 1,
                        density = 1f,
                        angle = 45f,
                        fillColor = Colors.RED,
                        bodyType = BodyType.KINEMATIC
                    )
                    //val boden3 = Rectangle(x = 0, y = -20, width = 100, height = 1, density = 1f, angle = 0.5f, fillColor = Colors.LIGHTCORAL, bodyType = BodyType.KINEMATIC)


                    val image = resourcesVfs["gfx/hut.png"].readBitmap()

                    repeat(100) {
                        engine.registerBody(
                            Rectangle(
                                (-5..5).random(),
                                (3..5).random(),
                                restitution = 0.8f,
                                width = 0.2,
                                height = 0.2,
                                density = ((0..100).random().toFloat() / (1..10).random().toFloat()),
                                fillColor = Colors.GREEN,
                                bodyType = BodyType.DYNAMIC
                            )
                        )
                        engine.registerBody(
                            Circle(
                                (-5..5).random(),
                                (3..5).random(),
                                0.1,
                                bodyType = BodyType.DYNAMIC,
                                fillColor = Colors.PINK,
                                density = 0.5f
                            )
                        )
                        //   Engine.registerBody(Line((-50..50).random(), (-50..50).random(), (-50..50).random(), (-50..50).random(), BodyType.STATIC, Colors.LIGHTCORAL, 2))
                        engine.registerBody(
                            Image(
                                x = (-5..5).random(),
                                y = (3..5).random(),
                                bodyType = BodyType.DYNAMIC,
                                density = 0.5f,
                                friction = 0.3f,
                                preInitializedBitmap = image
                            )
                        )
                    }


                    //Engine.registerBody(test)

                    engine.registerBody(boden)
                    // Engine.registerBody(line)
                    engine.registerBody(boden2)
                    //Engine.registerBody(boden3)
                    //Engine.registerBody(Line(0, 0, 80, 0, fillColor = Colors.AQUA, thickness = 1))
                    //Engine.registerBody(test2)
                }

                /**
                 * Code, der NACH dem Aufbau des Views ausgeführt wird
                 */
                viewDidLoad {

                }

                start()
            }
        }
    }
}