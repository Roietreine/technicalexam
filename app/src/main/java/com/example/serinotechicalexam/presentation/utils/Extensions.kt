package com.example.serinotechicalexam.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.glideImage(image: Any?) = Glide.with(this).load(image).into(this)
