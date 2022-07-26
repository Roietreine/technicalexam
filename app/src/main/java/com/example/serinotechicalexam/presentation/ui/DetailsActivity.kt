package com.example.serinotechicalexam.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.databinding.ActivityDetailsBinding
import com.example.serinotechicalexam.presentation.utils.glideImage
import com.google.gson.Gson

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val product by lazy {
        Gson().fromJson(intent.getStringExtra(PRODUCT_EXTRA), Product::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            binding.detailsTitle.text = product.title
            binding.detailsDesc.text = product.description
            binding.detailsImage.glideImage(product?.images.toString())
            binding.detailsPrice.text = product.price
        }

    }

    companion object {

        const val PRODUCT_EXTRA = "product_extra"
        fun createIntent(context: Context, product: Product) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(PRODUCT_EXTRA, Gson().toJson(product))
            }
    }
}