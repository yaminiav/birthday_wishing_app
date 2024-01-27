package com.example.birthdayfafa

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_secondphase.*
import kotlinx.android.synthetic.main.example_ticket.view.*
import kotlinx.android.synthetic.main.image_ticket.view.*

class secondphase : AppCompatActivity() {
    var listOfServices=ArrayList<Written>()
    var adapter:HumansAdapter?=null
    var b:Bundle?=null
    var media: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondphase)
        media=MediaPlayer.create(this,R.raw.vayari)
        media!!.start()
        listOfServices.add(Written("Special",R.drawable.special))
        listOfServices.add(Written("Videos",R.drawable.video))
        listOfServices.add(Written("Memes",R.drawable.memes))
        listOfServices.add(Written("Edho le",R.drawable.edho))
        b=intent.extras
        adapter = HumansAdapter(this, listOfServices)
        secondview.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        media!!.pause()
        anime.isVisible=false
    }
    inner class HumansAdapter(var context: Context, var listOfServices:ArrayList<Written>): BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val service=listOfServices[position]
            val myview =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.image_ticket,
                    null
                ).also {
                    it.photo.setImageResource(service.image!!)
                    it.imagetext.text = service.name
                }
            myview.setOnClickListener {
                val intent:Intent
                if (service.name.equals("Special",true)){
                    intent=Intent(context,writtenmsg::class.java)
                    intent.putExtra("written",b!!.get("written") as ArrayList<readEvery>)
                }
                else if(service.name.equals("memes",true)){
                    intent=Intent(context,memes::class.java)
                    intent.putExtra("memes",b!!.get("memes") as ArrayList<readEvery>)
                }
                else if(service.name.equals("edho le",true)){
                    intent=Intent(context,edhole::class.java)
                    intent.putExtra("edho",b!!.get("edho") as ArrayList<readEvery>)
                }
                else{
                    intent=Intent(context,videomsg::class.java)
                    intent.putExtra("videos",b!!.get("videos") as ArrayList<readEvery>)
                }
               context.startActivity(intent)
            }
            return  myview
        }
        override fun getCount(): Int {
           return listOfServices.size
        }
        override fun getItem(position: Int): Any {
            return listOfServices[position]
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
}