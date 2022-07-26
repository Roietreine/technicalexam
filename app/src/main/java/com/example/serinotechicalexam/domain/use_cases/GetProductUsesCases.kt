package com.example.serinotechicalexam.domain.use_cases

import com.example.serinotechicalexam.domain.repository.ProductRepo
import javax.inject.Inject

class GetProductUsesCases @Inject constructor(private val productRepo: ProductRepo) {

    suspend operator fun invoke(page : Int) = productRepo.getProduct(page)
}