package com.example.birthdayfafa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_msginfo.*

class msginfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msginfo)
        val b=intent.extras
        msgtext.text = b!!.getString("name")
        Glide.with(this).load(b.getString("image")).override(250,250).into(msgimage)
        des.text = b.getString("des")
        number.text = b.getString("number")
        msgimage.setOnClickListener {
            val intent=Intent(this,bigImage::class.java)
            intent.putExtra("image",b.getString("image"))
            startActivity(intent)
        }
    }

    fun whatspp(view: View) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", number.text.toString(),
                    String.format("\"%s\"\n- %s",des.text.toString(),msgtext.text)+"\n\n\nThank you"
                    )
                )
            )
        )
    }
}

