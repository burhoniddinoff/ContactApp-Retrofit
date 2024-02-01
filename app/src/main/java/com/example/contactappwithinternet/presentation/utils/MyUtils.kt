package com.example.contactappwithinternet.presentation.utils

import androidx.viewbinding.ViewBinding
import com.example.contactappwithinternet.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


fun FragmentActivity.addScreen(fm: Fragment) {
    supportFragmentManager.beginTransaction().add(R.id.container, fm).commit()
}

fun FragmentActivity.replaceScreen(fm: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.container, fm)
        .addToBackStack(fm::class.java.name).commit()
}

fun FragmentActivity.replaceScreenWithoutSave(fm: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.container, fm).commit()
}


fun FragmentActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}

fun Fragment.replaceScreen(fm: Fragment) {
    requireActivity().replaceScreen(fm)
}

fun Fragment.replaceScreenWithoutSave(fm: Fragment) {
    requireActivity().replaceScreenWithoutSave(fm)
}

fun Fragment.popBackStack() {
    requireActivity().popBackStack()
}

fun String.myLog() = Log.d("TTT", this)
fun String.onlyLetters() = all { it.isLetter() }

fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}



