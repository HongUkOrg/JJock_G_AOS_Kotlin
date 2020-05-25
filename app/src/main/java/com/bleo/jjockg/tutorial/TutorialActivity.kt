package com.bleo.jjockg.tutorial

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bleo.jjockg.R
import com.bleo.jjockg.databinding.TutorialMainBinding
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.viewpager.pageSelections
import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
import com.perelandrax.reactorkit.ReactorView
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo

class TutorialActivity : RxAppCompatActivity(), ReactorView<TutorialReactor> {

    // UI
    private lateinit var viewPager: ViewPager
    private lateinit var finderImageView: ImageView
    private lateinit var skipButton: Button

    private val pageRelay: BehaviorRelay<Int> = BehaviorRelay.createDefault(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_main)

        Log.d("bleoLog", "tutorial Reactor")
        val dataBinder: TutorialMainBinding = DataBindingUtil.setContentView(this, R.layout.tutorial_main)

        // UI Binding
        viewPager = dataBinder.cartoonViewPager
        finderImageView = dataBinder.finder
        skipButton = dataBinder.btnMainSkip

        viewPager.adapter = TutorialViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        createReactor(TutorialReactor())
        dataBinder.tutorialReactor = reactor
    }

    // MARK: - Binding
    override fun bind(reactor: TutorialReactor) {

        viewPager
            .pageSelections()
            .map { TutorialReactor.Action.UpdatePage(it) }
            .subscribe(reactor.action)
            .addTo(disposables)

        pageRelay
            .distinctUntilChanged()
            .map { TutorialReactor.Action.UpdatePage(it) }
            .subscribe(reactor.action)
            .addTo(disposables)

        skipButton
            .clicks()
            .map { TutorialReactor.Action.TapSkipButton }
            .subscribe(reactor.action)
            .addTo(disposables)

    }
}
