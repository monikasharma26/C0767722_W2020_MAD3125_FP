package com.example.c0767722_w2020_mad3125_fp.Actvities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.InjectView
import com.example.c0767722_w2020_mad3125_fp.R
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.inject(this)
        val btnLogin = findViewById(R.id.btnLogin) as Button
        val btnSignUp = findViewById(R.id.btnSignUp) as Button
        val bar = supportActionBar
        bar!!.hide()
        btnLogin.setOnClickListener {
            val intent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener {
            val intent = Intent(this@HomeActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}