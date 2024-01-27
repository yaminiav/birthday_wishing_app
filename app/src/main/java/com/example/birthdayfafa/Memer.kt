package com.example.birthdayfafa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_memer.*

class Memer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memer)
        val b=intent.extras
        memtex.text=b!!.getString("des")
        Glide.with(this).load(b.getString("image")).into(imagiew)
    }
}