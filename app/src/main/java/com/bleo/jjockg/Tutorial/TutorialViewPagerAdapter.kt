package com.bleo.jjockg.Tutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bleo.jjockg.R

class TutorialViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    private val cartoons: Array<TutorialCartoonFragment> = arrayOf(
        TutorialCartoonFragment(R.layout.fragment_cartoon01),
        TutorialCartoonFragment(R.layout.fragment_cartoon02),
        TutorialCartoonFragment(R.layout.fragment_cartoon03),
        TutorialCartoonFragment(R.layout.fragment_cartoon04),
        TutorialCartoonFragment(R.layout.fragment_cartoon05),
        TutorialCartoonFragment(R.layout.fragment_cartoon06),
        TutorialCartoonFragment(R.layout.fragment_cartoon07),
        TutorialCartoonFragment(R.layout.fragment_cartoon08)
    )

    override fun getItem(position: Int): Fragment {
        return cartoons[position]
    }

    override fun getCount(): Int {
        return cartoons.size
    }

}