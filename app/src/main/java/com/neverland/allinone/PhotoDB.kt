package com.neverland.allinone

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoModel::class], version = DATABASE_VERSION)
abstract class PhotoDB: RoomDatabase() {
    abstract fun getPhotoDao():PhotoDao
}
