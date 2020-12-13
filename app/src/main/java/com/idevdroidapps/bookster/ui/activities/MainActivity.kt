package com.idevdroidapps.bookster.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.idevdroidapps.bookster.Injection
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.databinding.ActivityMainBinding
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewModelProvider(this@MainActivity, Injection.provideMainActViewModelFactory()).get(
            SharedViewModel::class.java
        )
        binding.lifecycleOwner = this
    }
}