package com.example.selflearning

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.selflearning.databinding.ActivityMainBinding
import com.example.selflearning.fragment.login.RegistrationFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    private fun initViews() {

        binding.tvSignup.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.main, RegistrationFragment()).commit();
        }
    }
}
