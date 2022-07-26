package com.example.serinotechicalexam.data.dto

data class ProductsDetails (

val products: List<Product>,
val total: Int,
val skip: Int,
val limit: Int
)
