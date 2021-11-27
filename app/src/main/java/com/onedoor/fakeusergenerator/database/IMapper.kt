package com.onedoor.fakeusergenerator.database

import com.onedoor.fakeusergenerator.FakeUser

interface IMapper {
    fun toObject(dataModel: Any) : List<FakeUser>
}