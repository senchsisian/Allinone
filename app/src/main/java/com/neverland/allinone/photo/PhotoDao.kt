package com.neverland.allinone.photo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.neverland.allinone.PHOTOS
import com.neverland.allinone.SELECT_FROM

@Dao
interface PhotoDao {

    @Insert
    fun addPhotos(vararg photos: PhotoModel)

    @Query("$SELECT_FROM $PHOTOS")
    fun getAllPhoto():List<PhotoModel>

    @Delete
    fun removePhoto(vararg photos: PhotoModel)
}