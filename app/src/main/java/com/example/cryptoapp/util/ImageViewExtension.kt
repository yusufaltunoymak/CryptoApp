package com.example.cryptoapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load

fun ImageView.loadImage(path: String?) {
    val placeHolder = createPlaceHolder(this.context)
    this.load(Constants.IMAGE + path){
        crossfade(true)
        crossfade(500)
        placeholder(placeHolder)
    }

}
private fun createPlaceHolder(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}