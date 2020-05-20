package com.bleo.jjockg.Splash

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bleo.jjockg.R
import com.bleo.jjockg.databinding.SplashMainBinding
import com.perelandrax.reactorkit.ReactorView
import com.perelandrax.reactorkit.extras.bind
import com.perelandrax.reactorkit.extras.disposed
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

class SplashActivity : RxAppCompatActivity(), ReactorView<SplashReactor> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)

        createReactor(SplashReactor())
        val dataBinding: SplashMainBinding = DataBindingUtil.setContentView(this, R.layout.splash_main)
    }

    override fun bind(reactor: SplashReactor) {

        lifecycle()
            .filter { it == ActivityEvent.CREATE }
            .map { SplashReactor.Action.viewOnCreated }
            .bind(to = reactor.action)
            .disposed(by = disposeBag)
    }
}