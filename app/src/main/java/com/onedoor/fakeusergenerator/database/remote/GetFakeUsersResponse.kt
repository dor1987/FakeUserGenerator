package com.onedoor.fakeusergenerator.database.remote

data class GetFakeUsersResponse(
    val results: List<Result>
) {
    data class Result(
        val dob: Dob,
        val email: String,
        val name: Name,
        val picture: Picture
    ) {
        data class Dob(
            val age: Int,
            val date: String
        )

        data class Name(
            val first: String,
            val last: String,
            val title: String
        )

        data class Picture(
            val large: String,
            val medium: String,
            val thumbnail: String
        )
    }
}