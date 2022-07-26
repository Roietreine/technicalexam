package com.example.serinotechicalexam.domain.repository

import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.data.dto.ProductsDetails
import kotlinx.coroutines.flow.Flow

interface ProductRepo {

    suspend fun  getProduct(page : Int) : Flow<ProductsDetails>
    suspend fun  getProductById(id : String) : Flow<Product>
}