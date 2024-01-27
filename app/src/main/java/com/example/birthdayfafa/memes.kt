package com.example.birthdayfafa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_memes.*
import kotlinx.android.synthetic.main.image_ticket.view.*
import java.util.*
import kotlin.collections.ArrayList

class memes : AppCompatActivity() {
    var listOfMemes=ArrayList<readEvery>()
    var adapter: MemeAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memes)
        val b=intent.extras
        listOfMemes= b!!.get("memes") as ArrayList<readEvery>
        Collections.sort(listOfMemes, compareBy { it.name })
        adapter = MemeAdapter(this, listOfMemes)
        memegrid.adapter = adapter
    }
    inner class MemeAdapter(var context: Context, var listOfmsgs: ArrayList<readEvery>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val msg=listOfmsgs[position]
            val myView =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.image_ticket,
                    null
                )
            myView.imagetext.isVisible=false
            Glide.with(context).load(msg.image).centerCrop().override(250, 250).into(myView.photo)
            myView.setOnClickListener {
                val intent=Intent(context, Memer::class.java)
                intent.putExtra("image", msg.image)
                intent.putExtra("des", msg.message)
                context.startActivity(intent)
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