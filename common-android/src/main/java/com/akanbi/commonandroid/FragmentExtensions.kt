package com.akanbi.commonandroid

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    tag: String,
    idContainer: Int,
    enterAnimation: Int = 0,
    exitAnimation: Int = 0,
    popEnterAnimation: Int = 0,
    popExitAnimation: Int = 0
) {

    val transaction = supportFragmentManager.beginTransaction()

    supportFragmentManager.executePendingTransactions()

    transaction.setCustomAnimations(
        enterAnimation,
        exitAnimation,
        popEnterAnimation,
        popExitAnimation
    ).replace(
        idContainer,
        fragment,
        tag
    ).setReorderingAllowed(true).commitAllowingStateLoss()
}

fun AppCompatActivity.replaceFragmentWithStack(
    fragment: Fragment,
    tag: String,
    idContainer: Int,
    enterAnimation: Int = 0,
    exitAnimation: Int = 0,
    popEnterAnimation: Int = 0,
    popExitAnimation: Int = 0
) {

    val transaction = supportFragmentManager.beginTransaction()

    supportFragmentManager.executePendingTransactions()

    transaction.setCustomAnimations(
        enterAnimation,
        exitAnimation,
        popEnterAnimation,
        popExitAnimation
    ).add(
        idContainer,
        fragment,
        tag
    ).addToBackStack(tag)
        .setReorderingAllowed(true)
        .commitAllowingStateLoss()
}


fun AppCompatActivity.removeFragment(
    tag: String,
    enterAnimation: Int = 0,
    exitAnimation: Int = 0,
    popEnterAnimation: Int = 0,
    popExitAnimation: Int = 0
) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    if (fragment != null) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            enterAnimation,
            exitAnimation,
            popEnterAnimation,
            popExitAnimation
        ).remove(fragment).commitNow()
        supportFragmentManager.popBackStack()

    }
}