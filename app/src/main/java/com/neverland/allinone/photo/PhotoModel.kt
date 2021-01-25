package com.neverland.allinone.photo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.neverland.allinone.PHOTOS

@Entity(tableName = PHOTOS)
data class PhotoModel( val id: Int?,@PrimaryKey() val title: String){
    @Ignore var url:String?=""
}