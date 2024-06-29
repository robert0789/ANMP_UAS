package com.robert.anmp_uts.view

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhoto(imageView: ImageView, url: String?){

    if (!url.isNullOrEmpty()) {
        Picasso.get()
            .load(url)
            .into(imageView)
    } else {
        // Set a placeholder image when the URL is null or empty
        imageView.setImageResource(com.robert.anmp_uts.R.drawable.baseline_broken_image_24)  // Ensure you have a placeholder image in your resources
    }

}

