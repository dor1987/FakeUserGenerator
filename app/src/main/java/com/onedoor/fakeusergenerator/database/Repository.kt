package com.onedoor.fakeusergenerator.database

import com.onedoor.fakeusergenerator.models.FakeUser
import com.onedoor.fakeusergenerator.database.local.Dao
import com.onedoor.fakeusergenerator.database.local.LocalDataBaseMapper
import com.onedoor.fakeusergenerator.database.remote.ApiClient
import com.onedoor.fakeusergenerator.database.remote.RemoteDataBaseMapper
import java.lang.Exception


class Repository(
    private val fakeUsersDao: Dao,
    private val apiClient: ApiClient,
    private val remoteDataBaseMapper: RemoteDataBaseMapper,
    private val localDataBaseMapper: LocalDataBaseMapper
) : IRepository {

    override suspend fun getFakeUsersFromServer(
        noInfo: String,
        page: Int,
        amountOfResults: Int,
        seed: String,
        wantedParameters: String,
    ): List<FakeUser> {
        val request = apiClient.getFakeUsers(
            noInfo,
            page,
            amountOfResults,
            seed,
            wantedParameters,
        )

        if (!request.isSuccessful) {
            throw Exception("Server call failed "+ request.exception)
        }
        return remoteDataBaseMapper.toObject(request.body)
    }

    override suspend fun getFakeUsersFromLocalDataBase(): List<FakeUser> {
        return localDataBaseMapper.toObject(fakeUsersDao.getAllFakeUsers())
    }

    override suspend fun deleteLocalDataBase() {
        fakeUsersDao.deleteAll()
    }

    override suspend fun insertFakeUsersToLocalDataBase(fakeUsersList: List<FakeUser>) {
        fakeUsersDao.insertMultipleFakeUsers(
            *localDataBaseMapper.toLocalFakeUserObject(
                fakeUsersList
            ).toTypedArray()
        )
    }
}
