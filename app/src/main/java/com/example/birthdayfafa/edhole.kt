package com.example.birthdayfafa

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_edhole.*
import kotlinx.android.synthetic.main.activity_videomsg.*
import kotlinx.android.synthetic.main.image_ticket.view.*
import kotlin.collections.ArrayList

class edhole : AppCompatActivity() {
    var listOfEdhos=ArrayList<readEvery>()
    var adapter:EdhoAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edhole)
        val b=intent.extras
        listOfEdhos=b!!.get("edho") as ArrayList<readEvery>
        adapter = EdhoAdapter(this, listOfEdhos)
        edhogrid.adapter = adapter
    }
    inner class EdhoAdapter(var context: Context, var listOfVideos: ArrayList<readEvery>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vidd=listOfVideos[position]
            val myView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.image_ticket,
                    null
                )
            myView.imagetext.isVisible=false
            Glide.with(context).asBitmap().load(Uri.parse(vidd.image)).centerCrop().override(80, 80).diskCacheStrategy(
                DiskCacheStrategy.DATA
            ).into(myView.photo)
            myView.setOnClickListener {
                val i= Intent(context, videoinfo::class.java)
                i.putExtra("video", vidd.image)
                Log.d("nammm","$vidd.image")
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