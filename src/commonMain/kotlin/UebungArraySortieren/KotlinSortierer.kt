package UebungArraySortieren

import engineEmi.ScreenElements.CanvasElements.Rechteck


object KotlinSortierer : Sortieralgorithmus() {
    override suspend fun sortieren(array: Array<Rechteck>) {
        array.sortBy { it.hoehe.toInt() }
    }
}
