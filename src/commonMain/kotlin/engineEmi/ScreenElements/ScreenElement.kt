package engineEmi.ScreenElements

import com.soywiz.korev.MouseEvent
import engineEmi.Input.MouseEventReacteable

interface ScreenElement : MouseEventReacteable {
    override fun reactToMouseEvent(event: MouseEvent) {}
}