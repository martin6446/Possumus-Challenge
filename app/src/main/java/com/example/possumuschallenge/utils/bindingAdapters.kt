package com.example.possumuschallenge.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

@BindingAdapter("bindLoadPhoto")
fun loadPhoto(view: ImageView, url: String){

    //Glide wasn't able to load the image
    // found this answer on:
    // https://stackoverflow.com/questions/69576936/glide-filenotfoundexception/69637520#69637520?newreg=d9391d0b20c047f8bea8780b1745af95
    val image = GlideUrl(
        url,LazyHeaders.Builder()
            .addHeader("User-Agent", "5")
            .build()
    )
    Glide.with(view.context).asBitmap().load(image).into(view)
}