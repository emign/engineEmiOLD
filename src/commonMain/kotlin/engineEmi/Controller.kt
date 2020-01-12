package engineEmi

import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import engineEmi.Input.KeyEventReacteable
import engineEmi.Input.MouseEventReacteable

/**
 * Controller sind Objekte, die nicht als [ScreenElement] auf dem View dargestellt werden, aber trotzdem
 * z.B. Events entgegen nehmen können oder Anweisungen an andere Objekte schicken können.
 * Natürlich können auch [ScreenElement]e Controller sein
 */
interface Controller : MouseEventReacteable, KeyEventReacteable {
    override fun reactToMouseEvent(event: MouseEvent) {

    }

    override fun reactToKeyEvent(event: KeyEvent) {

    }
}