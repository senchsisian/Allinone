package com.neverland.allinone.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface GetMethodUsers {
    @GET ("api/users?page=2")
    fun getUsersFunction(): Call<UserModel>
}

interface GetMethodProducts {
    @GET ("api/public-api/products/2")
    fun getProductFunction(): Call<ProductModel>
}