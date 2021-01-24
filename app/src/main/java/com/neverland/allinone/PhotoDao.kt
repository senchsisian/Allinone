package com.neverland.allinone

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {

    @Insert
    fun addPhotos(vararg photos:PhotoModel)

    @Query("$SELECT_FROM $PHOTOS")
    fun getAllPhoto():List<PhotoModel>

    @Delete
    fun removePhoto(vararg photos:PhotoModel)
}