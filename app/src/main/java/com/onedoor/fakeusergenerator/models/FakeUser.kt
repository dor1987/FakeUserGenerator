package com.onedoor.fakeusergenerator.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FakeUser(
    val age: Int,
    val date: String,
    val email: String,
    val first: String,
    val last: String,
    val title: String,
    val large: String,
    val medium: String,
    val thumbnail: String,
    val id: Long
) : Parcelable