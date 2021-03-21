package ir.zinutech.android.flickrsearch.data.core.util

abstract class ListMapper<In, Out> : Mapper<List<In>, List<Out>> {
    override fun map(input: List<In>): List<Out> {
        return input.map {
            mapSingle(it)
        }
    }

    abstract fun mapSingle(input: In): Out
}