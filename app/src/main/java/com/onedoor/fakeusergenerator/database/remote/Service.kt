package com.onedoor.fakeusergenerator.database.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET(".")
    suspend fun getFakeUsers(
        @Query("noinfo") noinfo : String,
        @Query("page") pageNumber: Int,
        @Query("results") amountOfResults: Int,
        @Query("seed") seed: String,
        @Query("inc") inc: String
    ): Response<GetFakeUsersResponse>
}