package ir.zinutech.android.flickrsearch.core.extensions

import androidx.recyclerview.widget.RecyclerView

class CloseKeyboardScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            recyclerView.hideKeyboard()
        }
    }
}