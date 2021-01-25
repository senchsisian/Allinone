package com.neverland.allinone.photo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neverland.allinone.DATABASE_VERSION

@Database(entities = [PhotoModel::class], version = DATABASE_VERSION)
abstract class PhotoDB: RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
}
