package com.example.serinotechicalexam.data.datasource

import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.data.dto.ProductsDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductApi {

    @GET("products")
    suspend fun getProduct(@Query("skip") skip: Int, @Query("limit") limit: Int): ProductsDetails

    @GET("products/{id}")
    suspend fun getProductById(@Path("id")id : String) : Product

}