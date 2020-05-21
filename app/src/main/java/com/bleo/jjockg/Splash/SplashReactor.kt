package com.bleo.jjockg.Splash

import android.util.Log
import com.perelandrax.reactorkit.Reactor
import io.reactivex.Observable

class SplashReactor : Reactor<SplashReactor.Action, SplashReactor.Mutation, SplashReactor.State> {

    constructor() {
        Log.d("bleo", "SplashReactor Initialized")
    }

    override var initialState: State = State()

//    sealed class Action {
//        object viewOnCreated: Action()
//    }

    enum class Action(var parameter: Any? = null) {
        viewOnCreated,
        viewOnResume(10),
        ;
    }


    sealed class Mutation {
        class playAnimation() : Mutation()
    }

    class State(
        var splashPlayed: Boolean = false
    )

    override fun mutate(action: Action): Observable<Mutation> = when(action) {
        Action.viewOnCreated -> Observable.just(Mutation.playAnimation())
        else -> Observable.empty()
    }

    override fun reduce(state: State, mutation: Mutation): State = when(mutation) {

        is Mutation.playAnimation -> state.apply {
            Log.d("bleo", "play animation")
            splashPlayed = true
        }
    }
}