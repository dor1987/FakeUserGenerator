package com.onedoor.fakeusergenerator.database

import com.onedoor.fakeusergenerator.models.FakeUser

interface IRepository {
    suspend fun getFakeUsersFromServer(
        noInfo: String,
        page: Int,
        amountOfResults: Int,
        seed: String,
        wantedParameters: String,
    ): List<FakeUser>

    suspend fun getFakeUsersFromLocalDataBase() : List<FakeUser>

    suspend fun deleteLocalDataBase()

    suspend fun insertFakeUsersToLocalDataBase(fakeUsersList : List<FakeUser>)
}

