package com.andis.kigar.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface EndPointAPI {

    @Multipart
    @POST("addData")
    suspend fun uploadImage(
        @Part("description") description: RequestBody,
        @Part("ktp") file1: MultipartBody.Part,
        @Part("mesin") file2: MultipartBody.Part,
        @Part("garansi") file3: MultipartBody.Part,
        @Field("user_id") user_id: String
    ): Call<ResponseBody>

}