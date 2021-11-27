package com.onedoor.fakeusergenerator.database.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onedoor.fakeusergenerator.utils.DATABASE_NAME

@Database(entities = [LocalFakeUser::class], version = 1)
internal abstract class FakeUsersLocalDataBase : RoomDatabase() {
    abstract fun getFakeUsersDao(): Dao

    companion object {
        @Volatile
        private var instance: FakeUsersLocalDataBase? = null

        fun getInstance(context: Context): FakeUsersLocalDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FakeUsersLocalDataBase {
            return Room.databaseBuilder(context, FakeUsersLocalDataBase::class.java, DATABASE_NAME)
                .build()
        }
    }
}