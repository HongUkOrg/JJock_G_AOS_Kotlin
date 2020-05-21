package com.bleo.jjockg.Navigator

import android.content.Context
import android.util.Log

class JGNavigator {

    // MARK: - Properties
    companion object  {
        val getInstance: JGNavigator = JGNavigator()
    }

    private var currentContenxt: Context? = null

    // MARK: - Initialize
    fun setInitialContext(context: Context) {
        currentContenxt = context
    }

    // MARK: - Navigate
    fun naviate(step: JGNavigateStep) = when(step) {
        JGNavigateStep.splash -> { }
        JGNavigateStep.main -> {
            Log.d("bleo", "navigate to main")
        }
    }
}

enum class JGNavigateStep {
    splash,
    main
}