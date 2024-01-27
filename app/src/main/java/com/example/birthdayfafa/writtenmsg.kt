package com.example.birthdayfafa

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_writtenmsg.*
import kotlinx.android.synthetic.main.image_ticket.view.*
import java.util.*
import kotlin.collections.ArrayList

class writtenmsg : AppCompatActivity() {
    var listOfmsgs=ArrayList<readEvery>()
    var adapter: MessageAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writtenmsg)
        val b=intent.extras
        listOfmsgs= b!!.get("written") as ArrayList<readEvery>
        Collections.sort(listOfmsgs, compareBy { it.name})
        adapter = MessageAdapter(this, listOfmsgs)
        imagegrid.adapter = adapter
    }
    inner class MessageAdapter(var context: Context, var listOfmsgs: ArrayList<readEvery>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val msg=listOfmsgs[position]
            val myView =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.image_ticket,
                    null
                )
            myView.imagetext.text=msg.name
            myView.imagetext.setTextColor(Color.WHITE)
            myView.imagetext.setShadowLayer(2f,5f,5f, Color.BLACK)
            Glide.with(context).load(msg.image).centerCrop().override(250,250).into(myView.photo)
            myView.photo.setBackgroundColor(Color.WHITE)
            myView.setOnClickListener {
                if(msg.message.equals("")){
                    val i = Intent(context, bigImage::class.java)
                    i.putExtra("image", msg.image)
                    context.startActivity(i)
                }
                else {
                    val i = Intent(context, msginfo::class.java)
                    i.putExtra("name", msg.name)
                    i.putExtra("image", msg.image)
                    i.putExtra("des", msg.message)
                    i.putExtra("number", msg.number)
                    context.startActivity(i)
                }
            }
            return  myView
        }
        override fun getCount(): Int {
            return listOfmsgs.size
        }
        override fun getItem(position: Int): Any {
            return listOfmsgs[position]
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
}
