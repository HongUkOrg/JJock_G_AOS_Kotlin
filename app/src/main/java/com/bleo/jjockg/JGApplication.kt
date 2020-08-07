package com.bleo.jjockg

import android.app.Application

class JGApplication: Application() {

    companion object {
        const val TAG = "bleo"
    }

    override fun onCreate() {
        super.onCreate()
    }
}