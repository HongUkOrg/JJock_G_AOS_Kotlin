package com.bleo.jjockg.tutorial

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.bleo.jjockg.Navigator.JGNavigateStep
import com.bleo.jjockg.Navigator.JGNavigator
import com.bleo.jjockg.R
import com.perelandrax.reactorkit.Reactor
import com.bleo.jjockg.JGApplication.Companion.TAG

class TutorialReactor : Reactor<TutorialReactor.Action, TutorialReactor.Mutation, TutorialReactor.State> {

    override var initialState: State = State()

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

    sealed class Action {
        data class UpdatePage(val page: Int): Action()
        object TapSkipButton: Action()
    }

    sealed class Mutation {
        data class UpdatePage(val page: Int): Mutation()
        object TapSkipButton: Mutation()
    }

    open class State(
        var currentPage: ObservableInt = ObservableInt()
    )

    fun getReactorState(): State {
        return currentState
    }

    override fun mutate(action: Action): io.reactivex.rxjava3.core.Observable<Mutation> = when (action) {
        is Action.UpdatePage -> {
            io.reactivex.rxjava3.core.Observable.just(
                Mutation.UpdatePage(action.page)
            )
        }
        is Action.TapSkipButton -> {
            io.reactivex.rxjava3.core.Observable.just(Mutation.TapSkipButton)
        }
    }

    override fun reduce(state: State, mutation: Mutation): State = when (mutation) {
        is Mutation.UpdatePage -> {
            Log.d(TAG, "update page : ${mutation.page}")
            state.apply { currentPage.set(mutation.page) }
        }
        is Mutation.TapSkipButton -> {
            JGNavigator.getInstance.navigate(JGNavigateStep.Tutorial)
            state
        }
    }
}

@BindingAdapter("bind_image")
fun setPageControlImage(imageView: ImageView, currentPage: Int) {
    Log.d(TAG, "update image $currentPage")
    val finderImage = TutorialReactor.pageControlImageResourceId[currentPage]
    imageView.setImageResource(finderImage)
}