package com.onedoor.fakeusergenerator.viewmodels

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedoor.fakeusergenerator.database.Repository
import com.onedoor.fakeusergenerator.database.local.Dao
import com.onedoor.fakeusergenerator.database.local.LocalDataBaseMapper
import com.onedoor.fakeusergenerator.database.remote.NetworkLayer.apiClient
import com.onedoor.fakeusergenerator.database.remote.RemoteDataBaseMapper
import com.onedoor.fakeusergenerator.database.remote.WantedParametersBuilder
import com.onedoor.fakeusergenerator.models.FakeUser
import com.onedoor.fakeusergenerator.utils.SHARED_PREF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random

class FakeUserListFragmentViewModel(dao: Dao, application: Application) :
    ViewModel() {
    private val _fakeUsers = MutableLiveData<List<FakeUser>>(emptyList())
    val fakeUsers: LiveData<List<FakeUser>> = _fakeUsers

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    private val localMapper = LocalDataBaseMapper()
    private val remoteDataBaseMapper = RemoteDataBaseMapper()
    val isSwipeLoading = ObservableBoolean()
    val isInitLoading = ObservableBoolean()

    private val repository = Repository(
        dao,
        apiClient,
        remoteDataBaseMapper,
        localMapper
    )

    private val sharedPref = application.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    private val wantedParameters = WantedParametersBuilder(
        name = true,
        picture = true,
        email = true,
        birthday = true
    ).toString()

    private val seed = getSeed()

    init {
        isInitLoading.set(true)
        loadData()
    }


    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val fakeUserListFromLocalDataBase = repository.getFakeUsersFromLocalDataBase()

            if (fakeUserListFromLocalDataBase.isEmpty()) {
                try {
                    val fakeUsersList = repository.getFakeUsersFromServer(
                        noInfo = noInfo,
                        page = sharedPref.getInt(pageSharedPrefKey, 1),
                        amountOfResults = amountOfResults,
                        seed = seed,
                        wantedParameters
                    )
                    repository.insertFakeUsersToLocalDataBase(fakeUsersList)
                    _fakeUsers.postValue(fakeUsersList)

                } catch (e: Exception) {
                    _errors.postValue(e)
                    _fakeUsers.postValue(emptyList<FakeUser>())

                } finally {
                    isInitLoading.set(false)
                }
            } else {
                _fakeUsers.postValue(fakeUserListFromLocalDataBase)
                isInitLoading.set(false)
            }
        }
    }

    fun onRefresh() {
        isSwipeLoading.set(true)
        with(sharedPref.edit()) {
            putInt(pageSharedPrefKey, sharedPref.getInt(pageSharedPrefKey, 1) + 1)
            apply()
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fakeUserList = repository.getFakeUsersFromServer(
                    noInfo = noInfo,
                    page = sharedPref.getInt(pageSharedPrefKey, 1),
                    amountOfResults = amountOfResults,
                    seed = seed,
                    wantedParameters
                )

                repository.deleteLocalDataBase()
                repository.insertFakeUsersToLocalDataBase(fakeUserList)

                _fakeUsers.postValue(fakeUserList)

            } catch (e: Exception) {
                _errors.postValue(e)
                _fakeUsers.postValue(emptyList<FakeUser>())
            } finally {
                isSwipeLoading.set(false)
            }
        }
    }


    private fun getSeed(): String {
        if (!sharedPref.contains(seedSharedPrefKey)) {
            with(sharedPref.edit()) {
                putString(seedSharedPrefKey, generateSeedKey(10))
                apply()
            }
        }
        return sharedPref.getString(seedSharedPrefKey, "")!!
    }

    private fun generateSeedKey(seedKeyLength: Int): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..seedKeyLength)
            .map { i -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    companion object {
        private const val pageSharedPrefKey = "lastSeenPage"
        private const val seedSharedPrefKey = "seed"
        private const val amountOfResults = 10
        private const val noInfo = "noinfo"
    }
}