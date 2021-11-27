package com.onedoor.fakeusergenerator.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("DELETE FROM fakeUsers")
    fun deleteAll()

    @Insert
    fun insertMultipleFakeUsers(vararg fakeUsers: LocalFakeUser)

    @Query("SELECT * FROM fakeUsers")
    fun getAllFakeUsers(): List<LocalFakeUser>
}