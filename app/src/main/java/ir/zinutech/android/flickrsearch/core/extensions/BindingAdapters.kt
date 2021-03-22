package ir.zinutech.android.flickrsearch.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    load(url){
        crossfade(true)
        scale(Scale.FILL)
    }
}