package com.bleo.jjockg.tutorial

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bleo.jjockg.R
import com.bleo.jjockg.databinding.TutorialMainBinding
import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.reactorkit.ReactorView
import com.perelandrax.reactorkit.extras.bind
import com.perelandrax.reactorkit.extras.disposed
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

class TutorialActivity : RxAppCompatActivity(), ReactorView<TutorialReactor> {

    // UI
    private lateinit var viewPager: ViewPager
    private lateinit var finderImageView: ImageView

    private val pageRelay: BehaviorRelay<Int> = BehaviorRelay.createDefault(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_main)

        createReactor(TutorialReactor())
        Log.d("bleoLog", "tutorial Reactor")
        val dataBinder: TutorialMainBinding = DataBindingUtil.setContentView(this, R.layout.tutorial_main)
        dataBinder.tutorialReactor = reactor

        // UI Binding
        viewPager = dataBinder.cartoonViewPager
        finderImageView = dataBinder.finder

        viewPager.adapter = TutorialViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        viewPager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                pageRelay.accept(position)
                Log.d("bleoLog", "page scrolled ${position})")
            }
        })
    }

    // MARK: - Binding
    override fun bind(reactor: TutorialReactor) {

        pageRelay
            .distinctUntilChanged()
            .map { TutorialReactor.Action.UpdatePage(it) }
            .bind(to = reactor.action)
            .disposed(by = disposeBag)
    }
}