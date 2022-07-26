package com.example.serinotechicalexam.data.repository

import com.example.serinotechicalexam.data.datasource.ProductApi
import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.data.dto.ProductsDetails
import com.example.serinotechicalexam.domain.repository.ProductRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ProductRepoImp(private val productApi: ProductApi) : ProductRepo{
    override suspend fun getProduct(page: Int): Flow<ProductsDetails> = callbackFlow {
        val limit = 10
        val skip = (page - 1) * limit
        trySend(productApi.getProduct(skip, limit))
        awaitClose()
    }
    override suspend fun getProductById(id: String): Flow<Product> = callbackFlow {
        trySend(productApi.getProductById(id))
        awaitClose()
    }

}