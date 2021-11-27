package com.onedoor.fakeusergenerator.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onedoor.fakeusergenerator.models.FakeUser

class FakeUserInfoViewModelFactory(
    private val fakeUser: FakeUser
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FakeUserInfoFragmentViewModel::class.java)) {
            return FakeUserInfoFragmentViewModel(fakeUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
