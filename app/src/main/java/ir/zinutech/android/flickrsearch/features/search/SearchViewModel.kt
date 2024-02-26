package ir.zinutech.android.flickrsearch.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.usecases.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: dagger.Lazy<SearchUseCase>,
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    val uiState: LiveData<SearchUiState> = searchQuery
        .debounce(DEBOUNCE_TIME_IN_MILLIS)
        .asLiveData()
        .switchMap(::createUiState)


    private fun createUiState(query: @JvmSuppressWildcards String) = liveData {
        Timber.d("collectLatest(), query:[%s]", query)
        if (query.isEmpty()) {
            emit(SearchUiState.Idle)
            return@liveData
        }
        try {
            emit(SearchUiState.Loading)
            val photos = searchUseCase.get().invoke(query)
            if (photos.isEmpty()) {
                emit(SearchUiState.EmptyResult)
            } else {
                emit(SearchUiState.Success(photos))
            }
        } catch (e: Exception) {
            emit(SearchUiState.Error(e))
        }
    }

    fun onQueryChanged(query: String?) {
        query ?: return
        searchQuery.value = query
    }

    sealed class SearchUiState {
        data object Loading : SearchUiState()
        data object Idle : SearchUiState()
        data class Success(val photos: List<FlickrPhoto>) : SearchUiState()
        data object EmptyResult : SearchUiState()
        data class Error(val exception: Throwable) : SearchUiState()
    }

    companion object {
        private const val DEBOUNCE_TIME_IN_MILLIS = 300L
    }
}