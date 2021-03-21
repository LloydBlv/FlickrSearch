package ir.zinutech.android.flickrsearch.features.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.usecases.SearchUseCase
import ir.zinutech.android.flirckrsearch.core.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
        private val searchUseCase: SearchUseCase,
        @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _uiState = MutableLiveData<SearchUiState>()
    val uiState = _uiState

    init {
        viewModelScope.launch {
            searchQuery.debounce(DEBOUNCE_TIME_IN_MILLIS)
                    .collectLatest { query ->
                        Timber.d("collectLatest(), query:[%s]", query)
                        try {
                            _uiState.value = SearchUiState.Loading
                            val photos = withContext(ioDispatcher){
                                searchUseCase.invoke(query)
                            }
                            _uiState.value = SearchUiState.Success(photos)
                        } catch (e: Exception) {
                            _uiState.value = SearchUiState.Error(e)
                        }
                    }
        }
    }

    fun onQueryChanged(query: String) {
        searchQuery.value = query
    }

    sealed class SearchUiState {
        object Loading : SearchUiState()
        data class Success(val photos: List<FlickrPhoto>) : SearchUiState()
        data class Error(val exception: Throwable) : SearchUiState()
    }

    companion object {
        private const val DEBOUNCE_TIME_IN_MILLIS = 300L
    }
}