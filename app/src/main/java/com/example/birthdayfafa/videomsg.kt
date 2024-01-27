package com.example.birthdayfafa

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_videomsg.*
import kotlinx.android.synthetic.main.example_ticket.view.*
import java.util.*
import kotlin.collections.ArrayList

class videomsg : AppCompatActivity() {
    var listOfVideos=ArrayList<readEvery>()
    var adapter: VideoAdapter?=null
    var b:Bundle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videomsg)
        b=intent.extras
        listOfVideos=b!!.get("videos") as ArrayList<readEvery>
        Collections.sort(listOfVideos, compareBy { it.name })
        adapter = VideoAdapter(this, listOfVideos)
        videogrid.adapter = adapter
    }
    inner class VideoAdapter(var context: Context, var listOfVideos: ArrayList<readEvery>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vidd=listOfVideos[position]
            val myView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.example_ticket,
                    null
                )
            myView.vidText.text=vidd.name
            Glide.with(context).asBitmap().load(Uri.parse(vidd.image)).centerCrop().override(80, 80).diskCacheStrategy(
                DiskCacheStrategy.DATA
            ).into(myView.vid)
            myView.thankb.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", vidd.number,
                            "Thanks for the video wishes "+vidd.name
                        )
                        )
                    )
                )
            }
            myView.setOnClickListener {
                val i= Intent(context, videoinfo::class.java)
                /*i.putExtra("name", vidd.name)*/
                i.putExtra("video", vidd.image)
               /* i.putExtra("number", vidd.number)*/
                context.startActivity(i)
            }
            return  myView
        }
        override fun getCount(): Int {
            return listOfVideos.size
        }
        override fun getItem(position: Int): Any {
            return listOfVideos[position]
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
}