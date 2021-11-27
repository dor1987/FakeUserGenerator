package com.onedoor.fakeusergenerator.database.remote

import com.onedoor.fakeusergenerator.models.FakeUser

class  RemoteDataBaseMapper {
    fun toObject(response: GetFakeUsersResponse): List<FakeUser> {
        var fakeUserList = ArrayList<FakeUser>()

        response.results.forEach { fakeUserFromServer ->
            fakeUserList.add(
                FakeUser(
                    fakeUserFromServer.dob.age,
                    fakeUserFromServer.dob.date,
                    fakeUserFromServer.email,
                    fakeUserFromServer.name.first,
                    fakeUserFromServer.name.last,
                    fakeUserFromServer.name.title,
                    fakeUserFromServer.picture.large,
                    fakeUserFromServer.picture.medium,
                    fakeUserFromServer.picture.thumbnail,
                    id = 0
                )
            )
        }
        return fakeUserList
    }
}
