package com.onedoor.fakeusergenerator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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
