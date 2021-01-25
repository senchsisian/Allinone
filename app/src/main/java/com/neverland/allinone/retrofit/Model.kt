package com.neverland.allinone.retrofit

import com.google.gson.annotations.SerializedName

 class UserModel {
     @SerializedName("data")
     val data:ResponseData?=null

     class ResponseData{
         @SerializedName("id")
         val id:Int=0
         @SerializedName("email")
         val email:String=""
         @SerializedName("first_name")
         val firstName:String=""
         @SerializedName("last_name")
         val lastName:String=""
         @SerializedName("avatar")
         val avatar:String=""
     }
}

class ProductModel {
    @SerializedName("data")
    val data:ResponseData?=null

    class ResponseData{
        @SerializedName("id")
        val id:Int=0
        @SerializedName("name")
        val name:String=""
        @SerializedName("description")
        val description:String=""
        @SerializedName("price")
        val price:String=""
        @SerializedName("discount_amount")
        val discountAmount:String=""
    }
}