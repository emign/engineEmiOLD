package engineEmi

import com.soywiz.korev.MouseEvent
import engineEmi.Input.MouseEventReacteable

/**
 * Controller sind Objekte, die nicht als [ScreenElement] auf dem View dargestellt werden, aber trotzdem
 */
interface Controller : MouseEventReacteable {
    override fun reactToMouseEvent(event: MouseEvent) {

    }
}