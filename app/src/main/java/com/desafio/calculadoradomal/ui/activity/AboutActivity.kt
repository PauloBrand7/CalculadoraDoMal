package com.desafio.calculadoradomal.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.desafio.calculadoradomal.R


class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val imageView: ImageView = findViewById(R.id.bit)
        Glide.with(this).load(R.drawable.bit).into(imageView)
    }
}