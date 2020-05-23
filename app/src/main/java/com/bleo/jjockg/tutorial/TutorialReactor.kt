package com.bleo.jjockg.tutorial

import android.util.Log
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.bleo.jjockg.R
import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.reactorkit.Reactor
import io.reactivex.Observable

class TutorialReactor : Reactor<TutorialReactor.Action, TutorialReactor.Mutation, TutorialReactor.State> {

    override var initialState: State =
        State()

    sealed class Action {
        data class UpdatePage(val page: Int): Action()
    }

    sealed class Mutation {
        data class UpdatePage(val page: Int): Mutation()
    }

    open class State(
        var currentPage: ObservableInt = ObservableInt()
    )

    fun getReactorState(): State {
        return currentState
    }

    override fun mutate(action: Action): Observable<Mutation> = when (action) {
        is Action.UpdatePage -> {
            Observable.just(
                Mutation.UpdatePage(
                    action.page
                )
            )
        }
    }

    override fun reduce(state: State, mutation: Mutation): State = when (mutation) {
        is Mutation.UpdatePage -> {
            Log.d("bleoLog", "update page : ${mutation.page}")
            state.apply { currentPage.set(mutation.page) }
        }
    }

    companion object {
        val pageControlImageResourceId: Array<Int> = arrayOf(
            R.drawable.page_control_01,
            R.drawable.page_control_02,
            R.drawable.page_control_03,
            R.drawable.page_control_04,
            R.drawable.page_control_05,
            R.drawable.page_control_06,
            R.drawable.page_control_07,
            R.drawable.page_control_08
        )


    }
}

@BindingAdapter("bind_image")
fun setPageControlImage(imageView: ImageView, currentPage: Int) {
    val finderImage = TutorialReactor.pageControlImageResourceId[currentPage]
    imageView.setImageResource(finderImage)
}