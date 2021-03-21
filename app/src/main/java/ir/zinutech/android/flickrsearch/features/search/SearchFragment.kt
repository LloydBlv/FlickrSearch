package ir.zinutech.android.flickrsearch.features.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.zinutech.android.flickrsearch.R
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search_layout) {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        searchViewModel.uiState.observe(viewLifecycleOwner, {
            Timber.d("uiState.observe(), uiState:[%s]", it)
        })

        searchViewModel.onQueryChanged("hack")

    }
}