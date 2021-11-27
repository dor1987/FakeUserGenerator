package com.onedoor.fakeusergenerator.database.local

import com.onedoor.fakeusergenerator.FakeUser

class LocalDataBaseMapper {
    fun toObject(localFakeUserDataList: List<LocalFakeUser>): List<FakeUser> {
        var fakeUserList = ArrayList<FakeUser>()

        localFakeUserDataList.forEach { fakeUserFromLocalDataBase ->
            fakeUserList.add(
                FakeUser(
                    fakeUserFromLocalDataBase.age,
                    fakeUserFromLocalDataBase.date,
                    fakeUserFromLocalDataBase.email,
                    fakeUserFromLocalDataBase.first,
                    fakeUserFromLocalDataBase.last,
                    fakeUserFromLocalDataBase.title,
                    fakeUserFromLocalDataBase.large,
                    fakeUserFromLocalDataBase.medium,
                    fakeUserFromLocalDataBase.thumbnail,
                    fakeUserFromLocalDataBase.id
                )
            )
        }
        return fakeUserList
    }

    fun toLocalFakeUserObject(fakeUserList: List<FakeUser>): List<LocalFakeUser> {
        var fakeLocalUserList = ArrayList<LocalFakeUser>()
        fakeUserList.forEach { fakeUser ->
            fakeLocalUserList.add(
                LocalFakeUser(
                    fakeUser.age,
                    fakeUser.date,
                    fakeUser.email,
                    fakeUser.first,
                    fakeUser.last,
                    fakeUser.title,
                    fakeUser.large,
                    fakeUser.medium,
                    fakeUser.thumbnail
                )
            )
        }
        return fakeLocalUserList
    }
}