package UebungArraySortieren

import engineEmi.CanvasElements.Rechteck

object KotlinSortierer : Sortieralgorithmus() {
    override suspend fun sortieren(array: Array<Rechteck>) {
        array.sortBy { it.hoehe.toInt() }
    }
}
