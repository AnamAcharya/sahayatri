//package com.anam.sahayatri.api
//
//interface CustomerAPI {
//}

package api

import entity.Customer
import response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CustomerAPI {
    //Register user
    @POST("/users/register")
    suspend fun registerUser(
        @Body customer: Customer
    ): Response<UserResponse>


    @FormUrlEncoded
    @POST("users/login")
    suspend fun checkUser(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<UserResponse>


}