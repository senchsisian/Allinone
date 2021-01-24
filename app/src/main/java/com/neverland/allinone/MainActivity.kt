package com.neverland.allinone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

const val DATABASE_VERSION =5
const val PHOTOS="photos"
const val SELECT_FROM="SELECT * FROM"

class MainActivity : AppCompatActivity() {
    private lateinit var getFirstButton: RadioButton
    private lateinit var getSecondButton: RadioButton
    private lateinit var postButton: RadioButton
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private lateinit var photoDB: PhotoDB
    private val photoSet = mutableSetOf<PhotoModel>()
    private lateinit var getElement: PhotoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButton = findViewById(R.id.addButton)
        removeButton = findViewById(R.id.removeButton)
        getFirstButton = findViewById(R.id.get1Button)
        getSecondButton = findViewById(R.id.get2Button)
        postButton = findViewById(R.id.postButton)
        removeButton.isEnabled=false


        photoDB =
            Room.databaseBuilder(this.applicationContext, PhotoDB::class.java, PHOTOS)
                .build()

        addButton.setOnClickListener {
            removeButton.isEnabled=true
            when {
                getFirstButton.isChecked -> {
                    getSecondButton.isEnabled = false
                    postButton.isEnabled = false
                    val i = Random.nextInt()
                    getElement = PhotoModel(i, "James Bond $i")
                    photoSet.add(getElement)
                    GlobalScope.launch(Dispatchers.IO) {
                        photoDB.getPhotoDao().addPhotos(getElement)
                    }
                }
                getSecondButton.isChecked -> {
                    getFirstButton.isEnabled = false
                    postButton.isEnabled = false
                }
                postButton.isChecked -> {
                    getSecondButton.isEnabled = false
                    getFirstButton.isEnabled = false
                }
            }
        }

        removeButton.setOnClickListener {
            getFirstButton.isEnabled = true
            getSecondButton.isEnabled = true
            postButton.isEnabled = true
            removeButton.isEnabled=false

            when {
                getFirstButton.isChecked -> {
                    GlobalScope.launch(Dispatchers.IO) {
                        for (i in photoSet.size-1..0){
                            photoDB.getPhotoDao().removePhoto(photoSet.elementAt(i))
                        }
                        photoSet.clear()
                    }
                }
                getSecondButton.isChecked -> {
                }
                postButton.isChecked -> {
                }
            }
        }
    }

}