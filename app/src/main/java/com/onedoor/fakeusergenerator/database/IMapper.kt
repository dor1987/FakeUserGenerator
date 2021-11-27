package com.onedoor.fakeusergenerator.database

import com.onedoor.fakeusergenerator.models.FakeUser

interface IMapper {
    fun toObject(dataModel: Any) : List<FakeUser>
}