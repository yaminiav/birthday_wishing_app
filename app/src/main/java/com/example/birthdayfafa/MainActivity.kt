package com.example.birthdayfafa

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var database= FirebaseDatabase.getInstance()
    // Firebase realtime database name
    private var myRef=database.getReference("Data").ref
    var listOfmsgs=ArrayList<readEvery>()
    var listOfVideos=ArrayList<readEvery>()
    var listOfMemes=ArrayList<readEvery>()
    var listOfEdhos=ArrayList<readEvery>()
    var media: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        media=MediaPlayer.create(this,R.raw.kgf)
        media!!.start()
        for(i in 1..18){
            ldta(i)
        }
        for(i in 1..30){
            lData(i)
        }
        for (i in 1..18){
            loadData(i)
        }
        for(i in 1..8){
            loData(i)
        }
    }

    override fun onPause() {
        super.onPause()
        media!!.pause()
    }

    override fun onRestart() {
        super.onRestart()
        media!!.start()
    }
    fun ldta(i: Int) {
        // Replace according to your realtime database
        myRef.child("Edho le").child("$i")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val td = snapshot.value as HashMap<*, *>
                        listOfEdhos.add(
                            readEvery(
                                "", td["value"] as String,
                                "",""
                            )
                        )
                    } catch (ex: Exception) {

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
    fun lData(i: Int) {
        // Replace according to your realtime database
        myRef.child("Memes").child("$i")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val td = snapshot.value as HashMap<*, *>
                        listOfMemes.add(
                            readEvery(
                                td["title"] as String, td["value"] as String,
                                "",td["des"] as String
                            )
                        )
                    } catch (ex: Exception) {

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    fun verifier(view: View){
        if(view.id ==R.id.signinbu) {
            val username=login.text.toString()
            val password=password.text.toString()
            // Can be modified to maintian some realtime authentication
            if(username.trim().equals("username", true)){
                    if (password.trim().equals("password", true)){
                        media!!.stop()
                        val i = Intent(this, secondphase::class.java)
                        i.putExtra("written",listOfmsgs)
                        i.putExtra("videos",listOfVideos)
                        i.putExtra("memes",listOfMemes)
                        i.putExtra("edho",listOfEdhos)
                        startActivity(i)
                    }
                    else{
                        media!!.stop()
                        media=MediaPlayer.create(this,R.raw.kappaa)
                        media!!.start()
                      opener("password")
                    }
            }
            else{
                media!!.stop()
                media=MediaPlayer.create(this,R.raw.balaayya)
                media!!.start()
                opener("name")
            }
        }
    }
    fun opener(op:String){
        val settingsDialog=Dialog(this)
        settingsDialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        settingsDialog.setCancelable(true)
        // Change images accoring to your needs
        if(op.equals("password",ignoreCase = true)){
            settingsDialog.setContentView(
                layoutInflater.inflate(
                    R.layout.image_layout, null
                )
            )
        }else{
            settingsDialog.setContentView(
                layoutInflater.inflate(
                    R.layout.image2, null
                )
            )
        }
        settingsDialog.show()
    }
    fun loadData(i:Int){
        // Replace according to your realtime database
        myRef.child("Videos").child("$i")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val td = snapshot.value as HashMap<*, *>
                        listOfVideos.add(
                            readEvery(
                                td["title"] as String, td["value"] as String,
                                td["number"] as String,"crap"
                            )
                        )
                    } catch (ex: Exception) {
                        Log.d("hello-actex  ","${ex.cause}")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
    fun loData(i:Int){
        // Replace according to your realtime database
        myRef.child("Images").child("$i")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val td = snapshot.value as HashMap<*, *>
                        listOfmsgs.add(
                            readEvery(
                                td["title"] as String, td["value"] as String,
                                td["number"] as String,td["des"] as String
                            )
                        )
                    } catch (ex: Exception) {

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}