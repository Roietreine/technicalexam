package com.example.serinotechicalexam.domain.use_cases

import com.example.serinotechicalexam.domain.repository.ProductRepo
import javax.inject.Inject

class GetProductByIdUsesCases @Inject constructor(private val productRepo: ProductRepo) {

    suspend operator fun invoke (id : String) = productRepo.getProductById(id)

}