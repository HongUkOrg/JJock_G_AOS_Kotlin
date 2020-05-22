package com.bleo.jjockg.Tutorial

import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bleo.jjockg.R
import com.bleo.jjockg.databinding.TutorialMainBinding
import com.perelandrax.reactorkit.ReactorView
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

class TutorialActivity : RxAppCompatActivity(), ReactorView<TutorialReactor> {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_main)

        createReactor(TutorialReactor())
        val dataBinder: TutorialMainBinding = DataBindingUtil.setContentView(this, R.layout.tutorial_main)

        // UI Binding
        viewPager = dataBinder.cartoonViewPager

        viewPager.adapter = TutorialViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    }

    // MARK: - Binding
    override fun bind(reactor: TutorialReactor) {
//        viewPager.addOnAdapterChangeListener()
    }
}
