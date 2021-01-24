package com.neverland.allinone

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = PHOTOS)
data class PhotoModel( val id: Int?,@PrimaryKey() val title: String){
    @Ignore var url:String?=""
}