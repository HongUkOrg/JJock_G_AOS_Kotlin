package com.bleo.jjockg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bleo.jjockg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    // MARK: Initialize
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}
