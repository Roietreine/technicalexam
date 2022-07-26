package com.example.serinotechicalexam.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.databinding.ActivityMainBinding
import com.example.serinotechicalexam.presentation.adapter.MainAdapter
import com.example.serinotechicalexam.presentation.utils.UiEvents
import com.example.serinotechicalexam.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,MainAdapter.OnItemClick {

    private lateinit var binding : ActivityMainBinding
    private val productViewModel by viewModels<ProductViewModel>()
    private val mainAdapter by lazy {
        MainAdapter(this)
    }

    private var page = 1
    private var totalItems = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
    }
    private fun observeViewModel(){
        productViewModel.getProduct(page)
        productViewModel.productDetails.observe(this){
            when(it){
                is UiEvents.Success -> {
                    mainAdapter.setList(it.data.products)
                    totalItems = it.data.total
                    binding.productRecycler.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = mainAdapter
                        addOnScrollListener(object: RecyclerView.OnScrollListener(){
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                val visibleItems = layoutManager?.childCount ?: 0
                                val totalItemCount = layoutManager?.itemCount ?: 0
                                val firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                                if((totalItemCount < totalItems)) {
                                    if((visibleItems + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0){
                                        page++
                                        productViewModel.getProduct(page)
                                    }
                                }
                            }
                        })
                    }
                }
                is UiEvents.Error -> {

                }

                is UiEvents.Loading -> {

                }
            }
        }
    }

    override fun onItemClick(product: Product) {
        startActivity(DetailsActivity.createIntent(this,product))
    }

}