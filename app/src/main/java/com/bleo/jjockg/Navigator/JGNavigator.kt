package com.bleo.jjockg.Navigator

import android.content.Context
import android.content.Intent
import android.util.Log
import com.bleo.jjockg.tutorial.TutorialActivity

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
            val tutorialIntent = Intent(currentContenxt, TutorialActivity::class.java)
            tutorialIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            currentContenxt?.applicationContext?.startActivity(tutorialIntent)
            Log.d("bleoLog", "navigate to main")
        }
    }
}

enum class JGNavigateStep {
    splash,
    main
}