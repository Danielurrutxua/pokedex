package com.example.pokemon

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.model.Pokemon
import com.example.pokemon.pokelist.ItemAdapter

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Pokemon>?
) {
    val adapter = recyclerView.adapter as ItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("loadImage")
fun loadImage(image: ImageView, url: String?) {
    val url1 = "https://inmobiliariaibercasa.com/wp-content/themes/realestate-7/images/no-image.png"

    if (url == null) {
        Glide.with(image)
            .load(url1)
            .into(image)
    } else {
        Glide.with(image)
            .load(url)
            .into(image)
    }

}