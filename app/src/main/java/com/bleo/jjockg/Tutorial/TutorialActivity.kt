package com.bleo.jjockg.Tutorial

import android.os.Bundle
import com.bleo.jjockg.R
import com.perelandrax.reactorkit.ReactorView
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

class TutorialActivity : RxAppCompatActivity(), ReactorView<TutorialReactor> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        createReactor(TutorialReactor())
    }

    override fun bind(reactor: TutorialReactor) {


    }
}
