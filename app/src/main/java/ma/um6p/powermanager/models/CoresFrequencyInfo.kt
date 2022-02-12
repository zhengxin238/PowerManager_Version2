package ma.um6p.powermanager.models


class CoresFrequencyInfo(coreCount: Int) {

    val freqs: IntArray = IntArray(coreCount)
    val minFreqs: IntArray = IntArray(coreCount)
    val maxFreqs: IntArray = IntArray(coreCount)

}