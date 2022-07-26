package com.example.serinotechicalexam.presentation.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.databinding.ItemProductModelBinding
import com.example.serinotechicalexam.presentation.utils.glideImage


class MainAdapter(val listener : OnItemClick) : RecyclerView.Adapter<MainAdapter.AdapterHolder>() {

    private var productList = mutableListOf<Product>()

    class AdapterHolder(val adapts: ItemProductModelBinding) : RecyclerView.ViewHolder(adapts.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder =
        AdapterHolder(
            ItemProductModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        with(holder) {
            with(productList[position]) {
                adapts.productTitle.text = this.title
                adapts.productDesc.text = this.description
                adapts.productImage.glideImage(this?.images?.get(0) ?: "")
                adapts.productPrice.text = this.price
                adapts.productDesc.ellipsize = TextUtils.TruncateAt.MARQUEE
                adapts.productDesc.isSelected =true
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setList(products: List<Product>) {
        productList.addAll(products)
    }

    interface OnItemClick {
            fun onItemClick(product: Product)
    }
}