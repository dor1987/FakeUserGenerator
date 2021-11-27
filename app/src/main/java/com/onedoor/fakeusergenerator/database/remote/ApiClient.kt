package com.onedoor.fakeusergenerator.database.remote

import retrofit2.Response

class ApiClient(private val service: Service) {
    suspend fun getFakeUsers(
        noInfo: String,
        page: Int,
        amountOfResults: Int,
        seed: String,
        wantedParameters: String,
    ): SimpleResponse<GetFakeUsersResponse> {
        return safeApiCall {
            service.getFakeUsers(
                noInfo,
                page,
                amountOfResults,
                seed,
                wantedParameters,
            )
        }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}