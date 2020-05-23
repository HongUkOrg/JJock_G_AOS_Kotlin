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

    class State(
        var currentPage: Int = 0
    )

    fun getState(): State {
        return currentState
    }

    var currentPage = ObservableField<Int>()

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
            currentPage.set(mutation.page)
            state.apply { currentPage = mutation.page }
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
    Log.d("bleoLog", "image chnaged ${currentPage}")
    val finderImage = TutorialReactor.pageControlImageResourceId[currentPage]
    imageView.setImageResource(finderImage)
}