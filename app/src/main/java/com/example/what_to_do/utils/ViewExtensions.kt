package com.example.what_to_do.utils

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(
    lifecycleOwner: LifecycleOwner,
    snackbarMsg: LiveData<Int>,
    timeLength: Int = Snackbar.LENGTH_SHORT
){
    snackbarMsg.observe(lifecycleOwner){msg->
        Snackbar.make(this,msg,timeLength).show()
    }
}

fun String?.toTrimString(): String{
    return this.toString().trim()
}

fun TextView.showChar(
    lifecycleOwner: LifecycleOwner,
    charMsg: LiveData<String>,
) {
    charMsg.observe(lifecycleOwner){ msg ->
        if (msg.isEmpty()) {
            inVisible()
        } else {
            visible()
            text = "${msg.length}/20"
        }
    }
}

fun TextView.limitChar(
    lifecycleOwner: LifecycleOwner,
    charMsg: LiveData<String>,
) {
    charMsg.observe(lifecycleOwner){ msg ->
        if (msg.length <= 20) {
            inVisible()
        } else {
            visible()
            text = "title length max 20"
        }
    }
}



fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}