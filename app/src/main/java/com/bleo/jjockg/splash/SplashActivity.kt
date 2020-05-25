package com.bleo.jjockg.splash

import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import com.bleo.jjockg.Navigator.JGNavigator
import com.bleo.jjockg.R
import com.bleo.jjockg.databinding.SplashMainBinding
import com.bumptech.glide.Glide
import com.perelandrax.reactorkit.ReactorView
import com.trello.rxlifecycle4.android.ActivityEvent
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class SplashActivity : RxAppCompatActivity(), ReactorView<SplashReactor> {

    private lateinit var splashImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)

        JGNavigator.getInstance.setInitialContext(this)

        val dataBinder: SplashMainBinding = DataBindingUtil.setContentView(this, R.layout.splash_main)
        splashImageView = findViewById(R.id.mainCharacter)

        createReactor(SplashReactor())
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
            .map { SplashReactor.Action.AnimationPlayed }
            .delay(2000, TimeUnit.MILLISECONDS)
            .subscribe(reactor.action)
            .addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyReactor()
    }
}