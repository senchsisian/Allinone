package com.neverland.allinone

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.neverland.allinone.photo.PhotoDB
import com.neverland.allinone.photo.PhotoModel
import com.neverland.allinone.retrofit.GetMethodProducts
import com.neverland.allinone.retrofit.GetMethodUsers
import com.neverland.allinone.retrofit.ProductModel
import com.neverland.allinone.retrofit.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    private val photoList = mutableListOf<PhotoModel>()
    private var userList = mutableListOf<UserModel.ResponseData>()
    private var productList = mutableListOf<ProductModel.ResponseData>()
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
                    userList=getUsersInfo()
                }
                getSecondButton.isChecked -> {
                    getFirstButton.isEnabled = false
                    postButton.isEnabled = false
                    productList=getProductInfo()
                }
                postButton.isChecked -> {
                    getSecondButton.isEnabled = false
                    getFirstButton.isEnabled = false
                    funAddOrRemove(true,photoList)
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
                }
                getSecondButton.isChecked -> {
                }
                postButton.isChecked -> {
                    funAddOrRemove(false,photoList)
                }
            }
        }
    }

    private fun funAddOrRemove(canAdd:Boolean,someList:MutableList<PhotoModel>){
        when{
            canAdd->{
                val i = Random.nextInt()
                getElement = PhotoModel(i, "James Bond $i")
                someList.add(getElement)
                Log.v("photoSet","-$someList")
                GlobalScope.launch(Dispatchers.IO) {
                    photoDB.getPhotoDao().addPhotos(getElement)
                }

            }
            else->{
                GlobalScope.launch(Dispatchers.IO) {
                    for (i in someList.size-1..0){
                        photoDB.getPhotoDao().removePhoto(someList.elementAt(i))
                    }
                    someList.clear()
                }
            }
        }

    }

    private fun getUsersInfo():MutableList<UserModel.ResponseData>{
        var getData= mutableListOf<UserModel.ResponseData>()
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            val networkService = retrofit.create(GetMethodUsers::class.java)
            getData=networkService.getUsersFunction().execute().body()!!.data!!.toMutableList()
        }
        return getData
    }

    private fun getProductInfo(): MutableList<ProductModel.ResponseData>{
        var getData= mutableListOf<ProductModel.ResponseData>()
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://gorest.co.in/")
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            val networkService = retrofit.create(GetMethodProducts::class.java)
            getData=networkService.getProductFunction().execute().body()!!.data!!.toMutableList()
        }
        return getData
    }

}
