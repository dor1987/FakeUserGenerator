package com.onedoor.fakeusergenerator.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fakeUsers")
data class LocalFakeUser(
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first") val first: String,
    @ColumnInfo(name = "last") val last: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "large") val large: String,
    @ColumnInfo(name = "medium") val medium: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
