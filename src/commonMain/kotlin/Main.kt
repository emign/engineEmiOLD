import com.soywiz.korim.color.Colors
import engineEmi.Bodies.Ebody
import engineEmi.CanvasElements.*
import engineEmi.Engine


/**
 * Das Default (und eigentlich immer) das einzige Engine-Objekt
 */
val engine = Engine()

/**
 * Startpunkt für alle Programme.
 * Hier werden [Ebody] und [CanvasElement] Objekte bei der [Engine] registriert.
 * Hierzu kann man die Methoden [Engine.registerCanvasElement], [Engine.registerBody] und [Engine.register] verwenden.
 * Wir der Parameter sample auf true gesetzt, wird eine Funktionsdemo automatisch geladen
 * Beispiele kann man laden, indem man das Sample als Parameter in [Engine.run] übergibt
 * Zum Beispiel: engine.run(sample = Samples.INPUT)
 */
fun main() {
    engine.run {

        val sonne = Stern(
            50.0,
            Colors.YELLOW,
            Colors.YELLOW
        )

        val jupiter = Gasplanet(
            umlaufbahn(360, 0.04),
            Colors.BEIGE,
            Colors.BROWN,
            5,
            20
        )

        val merkur = Gesteinsplanet(
            umlaufbahn(120, 0.071),
            Colors.DARKGREY,
            Colors.DARKGREY,
            0,
            3
        )

        val erde = Gesteinsplanet(
            umlaufbahn(240, 0.025),
            Colors.DARKBLUE,
            Colors.DARKBLUE,
            2,
            6
        )

        val venus = Gesteinsplanet(
            umlaufbahn(180, 0.075),
            Colors.ORANGE,
            Colors.WHITE,
            0,
            6
        )

        val mars = Gesteinsplanet(
            umlaufbahn(300, 0.06),
            Colors.ORANGERED,
            Colors.WHITE,
            0,
            4
        )

        val saturn = Gasplanet(
            umlaufbahn(440, 0.015),
            Colors.BEIGE,
            Colors.ORANGE,
            4,
            18
        )

        val uranus = Gasplanet(
            umlaufbahn(510, 0.03),
            Colors.SKYBLUE,
            Colors.DARKBLUE,
            2,
            15
        )

        val neptun = Gasplanet(
            umlaufbahn(580, 0.005),
            Colors.MEDIUMBLUE,
            Colors.MIDNIGHTBLUE,
            2,
            15
        )

        val hintergrundbild = Bild(
            0,
            0,
            "Galaxie.jpg",
            1.0f
        )


        val Sonnensystem = arrayOf(hintergrundbild, sonne, jupiter, merkur, erde, venus, mars, saturn, uranus, neptun)

        engine.register(Sonnensystem)

    }
}

