package engineEmi.Bodies

import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.setView
import com.soywiz.korge.box2d.view
import com.soywiz.korge.input.onOver
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import org.jbox2d.dynamics.BodyType

class Image(
    x: Number = 0,
    y: Number = 0,
    bodyType: BodyType = BodyType.STATIC,
    var width: Number = 1,
    var height: Number = 1,
    angle: Float = 0f,
    val imageFile: String = "hut.png",
    density: Float = 1f,
    friction: Float = 0.2f,
    restitution: Float = 0.0f,
    var strokeColor: RGBA = Colors.BLUE,
    var strokeThickness: Double = 0.0,
    var scale: Float = 1f / 100f,
    val preInitializedBitmap: Bitmap? = null
) : Ebody(
    x = x, y = y, density = density, friction = friction, restitution = restitution, bodyType = bodyType
) {
    lateinit var image: Bitmap
    override var shape = BoxShape(width = width, height = height)

    init {
        setup()
        bd.angle = angle
    }

    override suspend fun initBody() {
        image = preInitializedBitmap ?: resourcesVfs[imageFile].readBitmap()
        width = image.width * scale
        height = image.height * scale
        shape = BoxShape(width = width, height = height)
        body = world.createBody(bd)
        fixture.density = density
        fixture.shape = shape
        fixture.friction = friction
        fixture.restitution = restitution
        body.createFixture(fixture)
        createView()
        body.setView(view)
    }

    override suspend fun createView() {
        view = Graphics(autoScaling = true).image(image) {
            position(x, y)
        }.scale(scale).anchor(.5, .5)
        view.apply {
            onOver {
                //writeInfo()
            }
        }
    }

    fun writeInfo() {
        println("Body: ${this.body.position.x}, ${this.body.position.y}")
        println("View: ${this.body.view!!.x}, ${this.body.view!!.y}")
    }
}



