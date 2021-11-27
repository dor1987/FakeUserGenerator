package com.onedoor.fakeusergenerator

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import cn.iwgang.countdownview.CountdownView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onedoor.fakeusergenerator.models.FakeUser

@BindingAdapter("first")
fun TextView.setFirst(item: FakeUser) {
    text = item.first
}

@BindingAdapter("title")
fun TextView.setTitle(item: FakeUser) {
    text = item.title
}

@BindingAdapter("last")
fun TextView.setLast(item: FakeUser) {
    text = item.last
}

@BindingAdapter("email")
fun TextView.setEmail(item: FakeUser) {
    text = item.email
}

@BindingAdapter("icon")
fun ImageView.setIcon(item: FakeUser) {
    Glide.with(this)
        .load(item.thumbnail)
        .apply(
            RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
        )
        .into(this)
}

@BindingAdapter("countDown")
fun CountdownView.startCountDown(time: Long) {
    start(time)
}

@BindingAdapter("largeImage")
fun ImageView.setLargeImage(item: FakeUser) {
    Glide.with(this)
        .load(item.large)
        .apply(
            RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
        )
        .into(this)
}
