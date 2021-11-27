package com.onedoor.fakeusergenerator.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onedoor.fakeusergenerator.database.local.Dao

class FakeUsersViewModelFactory(
    private val dao: Dao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FakeUserListFragmentViewModel::class.java)) {
            return FakeUserListFragmentViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
