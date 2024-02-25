package ir.zinutech.android.flickrsearch.features.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.zinutech.android.flickrsearch.R
import ir.zinutech.android.flickrsearch.core.extensions.CloseKeyboardScrollListener
import ir.zinutech.android.flickrsearch.core.extensions.toGone
import ir.zinutech.android.flickrsearch.core.extensions.toInvisible
import ir.zinutech.android.flickrsearch.core.extensions.toVisible
import ir.zinutech.android.flickrsearch.core.util.autoCleared
import ir.zinutech.android.flickrsearch.databinding.FragmentSearchLayoutBinding
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search_layout) {

    private val searchViewModel: SearchViewModel by viewModels()
    private val viewBinding: FragmentSearchLayoutBinding by viewBinding()
    private var searchResultAdapter by autoCleared<SearchAdapter>()
    private var keyboardCloseListener by autoCleared<CloseKeyboardScrollListener>()
    private var onSearchQueryTextListener: SearchView.OnQueryTextListener? = null
    private var errorSnackBar: Snackbar? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.onBackPressedDispatcher?.addCallback(this, enabled = true) {
            if (!isSearchViewOpen()) {
                isEnabled = false
                activity?.onBackPressed()
                return@addCallback
            }
            collapseSearchView()
        }
    }

    private fun collapseSearchView() {
        searchView?.isIconified = true
    }

    private fun isSearchViewOpen() = searchView?.isIconified == false

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        searchView = menu.findItem(R.id.action_search)?.actionView as? SearchView
        setupSearchMenuItem()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        onSearchQueryTextListener = null
        searchView = null
    }

    private fun setupSearchMenuItem() {
        searchView ?: return
        onSearchQueryTextListener = createSearchQueryTextListener()
        searchView?.setOnQueryTextListener(onSearchQueryTextListener)
    }

    private fun createSearchQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.onQueryChanged(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.onQueryChanged(newText)
                return true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservers()
    }

    private fun setupUi() {
        viewBinding.searchFragmentResultRv.apply {
            keyboardCloseListener = CloseKeyboardScrollListener()
            addOnScrollListener(keyboardCloseListener)
            adapter = SearchAdapter().also {
                searchResultAdapter = it
            }
        }
    }

    private fun setupObservers() {
        searchViewModel.uiState.observe(viewLifecycleOwner, ::bindUiState)
    }

    private fun bindUiState(uiState: SearchViewModel.SearchUiState?) {
        Timber.d("uiState.observe(), uiState:[%s]", uiState)
        viewBinding.searchFragmentResultPb.isVisible = uiState == SearchViewModel.SearchUiState.Loading
        dismissErrorSnackBar()
        when (uiState) {
            SearchViewModel.SearchUiState.Loading -> {
                showLoadingUi()
            }
            is SearchViewModel.SearchUiState.Success -> {
                showSearchResultUi(uiState)
            }

            is SearchViewModel.SearchUiState.Error -> {
                showErrorUi(uiState)
            }
            SearchViewModel.SearchUiState.Idle -> {
                showIdleUi()
            }
            SearchViewModel.SearchUiState.EmptyResult -> {
                showEmptyResultUi()
            }
            else -> {}
        }

    }

    private fun showEmptyResultUi() {
        viewBinding.searchFragmentSearchGuideTv.toVisible()
        viewBinding.searchFragmentResultRv.toInvisible()
        viewBinding.searchFragmentResultPb.toGone()
        showSnackBar(getString(R.string.search_has_no_result))
    }

    private fun showIdleUi() {
        viewBinding.searchFragmentSearchGuideTv.toVisible()
        viewBinding.searchFragmentResultRv.toInvisible()
        viewBinding.searchFragmentResultPb.toGone()
    }

    private fun showErrorUi(uiState: SearchViewModel.SearchUiState.Error) {
        val message = uiState.exception.localizedMessage ?: getString(R.string.something_went_wrong)
        showSnackBar(message)
    }

    private fun showSnackBar(message: CharSequence) {
        errorSnackBar = Snackbar.make(viewBinding.root, message, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.show()
    }

    private fun dismissErrorSnackBar() {
        errorSnackBar?.dismiss()
        errorSnackBar = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dismissErrorSnackBar()
    }

    private fun showSearchResultUi(uiState: SearchViewModel.SearchUiState.Success) {
        viewBinding.searchFragmentSearchGuideTv.toGone()
        viewBinding.searchFragmentResultRv.toVisible()
        searchResultAdapter.submitList(uiState.photos)
    }

    private fun showLoadingUi() {
        viewBinding.searchFragmentSearchGuideTv.toGone()
        viewBinding.searchFragmentResultRv.toInvisible()
    }
}