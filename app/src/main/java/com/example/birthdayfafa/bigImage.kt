package com.example.birthdayfafa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_big_image.*

class bigImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_image)
        val b=intent.extras
        Glide.with(this).load(b!!.getString("image")).into(bigview)
    }
}