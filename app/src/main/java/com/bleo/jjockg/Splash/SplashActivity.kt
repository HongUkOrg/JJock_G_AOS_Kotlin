package com.bleo.jjockg.Splash

import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bleo.jjockg.R
import com.bleo.jjockg.databinding.SplashMainBinding
import com.bumptech.glide.Glide
import com.perelandrax.reactorkit.ReactorView
import com.perelandrax.reactorkit.extras.bind
import com.perelandrax.reactorkit.extras.disposed
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

class SplashActivity() : RxAppCompatActivity(), ReactorView<SplashReactor> {

    private lateinit var splashImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)

        createReactor(SplashReactor())
        
        val dataBinder: SplashMainBinding = DataBindingUtil.setContentView(this, R.layout.splash_main)
        splashImageView = findViewById(R.id.mainCharacter)

        setup()
    }

    // MARK: Setup
    private fun setup() {

        Glide.with(this)
            .asGif()
            .load(R.drawable.splash_gif)
            .into(splashImageView)
    }

    // MARK: Binding
    override fun bind(reactor: SplashReactor) {

        lifecycle()
            .filter { it == ActivityEvent.CREATE }
            .map { SplashReactor.Action.viewOnCreated }
            .bind(to = reactor.action)
            .disposed(by = disposeBag)


    }
}