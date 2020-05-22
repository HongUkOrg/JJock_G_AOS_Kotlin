package com.bleo.jjockg.Tutorial

import android.util.Log
import com.perelandrax.reactorkit.Reactor
import io.reactivex.Observable

class TutorialReactor : Reactor<TutorialReactor.Action, TutorialReactor.Mutation, TutorialReactor.State> {

    override var initialState: State = State()

    sealed class Action {
        data class UpdatePage(val page: Int): Action()
    }

    sealed class Mutation {
        data class UpdatePage(val page: Int): Mutation()
    }

    class State(
        var currentPage: Int = 0
    )

    override fun mutate(action: Action): Observable<Mutation> = when (action) {
        is Action.UpdatePage -> {
            Observable.just(Mutation.UpdatePage(action.page))
        }
    }

    override fun reduce(state: State, mutation: Mutation): State = when (mutation) {
        is Mutation.UpdatePage -> {
            Log.d("bleo", "update page : ${mutation.page}")
            state.apply { currentPage = mutation.page }
        }
    }
}