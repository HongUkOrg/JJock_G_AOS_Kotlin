package com.bleo.jjockg.splash

import android.util.Log
import com.bleo.jjockg.Navigator.JGNavigateStep
import com.bleo.jjockg.Navigator.JGNavigator
import com.perelandrax.reactorkit.Reactor
import io.reactivex.rxjava3.core.Observable

class SplashReactor : Reactor<SplashReactor.Action, SplashReactor.Mutation, SplashReactor.State> {

    override var initialState: State = State()

    sealed class Action {
        object AnimationPlayed: Action()
    }

    sealed class Mutation {
        object NavigateToMain: Mutation()
    }

    class State(
        var splashPlayed: Boolean = false
    )

    override fun mutate(action: Action): Observable<Mutation> = when (action) {
        is Action.AnimationPlayed -> {
            Log.d("bleoLog", "animation played")
            Observable.just(Mutation.NavigateToMain)
        }
    }

    override fun reduce(state: State, mutation: Mutation): State = when (mutation) {

        is Mutation.NavigateToMain -> {
            JGNavigator.getInstance.naviate(JGNavigateStep.main)
            state
        }
    }
}