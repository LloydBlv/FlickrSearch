package ir.zinutech.android.flickrsearch.data.core.util

interface Mapper<In, Out> {
    fun map(input: In): Out
}