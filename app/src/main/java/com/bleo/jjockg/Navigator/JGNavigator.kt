package com.bleo.jjockg.Navigator

import android.content.Context
import android.content.Intent
import android.util.Log
import com.bleo.jjockg.tutorial.TutorialActivity

enum class JGNavigateStep {
    Splash,
    Tutorial
}

@Suppress("IMPLICIT_CAST_TO_ANY")
class JGNavigator {

    // MARK: - Properties
    companion object  {
        const val TAG = "bleo"
        val getInstance: JGNavigator = JGNavigator()
    }

    private var currentContext: Context? = null

    // MARK: - Initialize
    fun setInitialContext(context: Context) {
        currentContext = context
    }

    // MARK: - Navigate
    fun navigate(step: JGNavigateStep) = when(step) {

        JGNavigateStep.Splash -> {
            Log.d(TAG, "navigate to splash")
        }

        JGNavigateStep.Tutorial -> {
            Log.d(TAG, "navigate to tutorial")
            val tutorialIntent = Intent(currentContext, TutorialActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            currentContext?.applicationContext?.startActivity(tutorialIntent)
        }
    }
}