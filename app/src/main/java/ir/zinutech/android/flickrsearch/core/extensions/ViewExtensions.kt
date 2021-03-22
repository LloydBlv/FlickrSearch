package ir.zinutech.android.flickrsearch.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.ListAdapter


fun View.inflater(): LayoutInflater {
    return LayoutInflater.from(context)
}

fun ListAdapter<*, *>.clear() {
    submitList(null)
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.hideKeyboard() {
    val imm: InputMethodManager? = getSystemService(context, InputMethodManager::class.java)
    imm?.hideSoftInputFromWindow(windowToken, 0)
}