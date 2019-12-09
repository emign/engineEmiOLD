package UebungArraySortieren

import engineEmi.CanvasElements.Rechteck
import kotlinx.coroutines.delay

object BubbleSort : Sortieralgorithmus() {
    override suspend fun sortieren(array: Array<Rechteck>) {
        for (i in array.indices) {
            for (j in 0 until array.indices.last) {
                if (array[j].hoehe.toInt() > array[j + 1].hoehe.toInt()) {
                    array.tauscheIndexPositionen(j, j + 1)
                }
                ArrayController.positionenAktualisieren()
                delay(5)
            }
        }
    }
}