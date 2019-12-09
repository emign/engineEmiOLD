package UebungArraySortieren

import engineEmi.CanvasElements.Rechteck

abstract class Sortieralgorithmus {
    abstract suspend fun sortieren(array: Array<Rechteck>)


    override fun toString(): String {
        return "Sortiere mit: ${this::class}"
    }


}