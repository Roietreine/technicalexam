package com.example.serinotechicalexam.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serinotechicalexam.data.dto.Product
import com.example.serinotechicalexam.data.dto.ProductsDetails
import com.example.serinotechicalexam.domain.use_cases.GetProductByIdUsesCases
import com.example.serinotechicalexam.domain.use_cases.GetProductUsesCases
import com.example.serinotechicalexam.presentation.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProduct: GetProductUsesCases,
    private val getProductById: GetProductByIdUsesCases
) : ViewModel() {

    private val _productDetails = MutableLiveData<UiEvents<ProductsDetails>>()
    val productDetails: LiveData<UiEvents<ProductsDetails>>
        get() = _productDetails

    private val _product = MutableLiveData<UiEvents<Product>>()
    val product: LiveData<UiEvents<Product>> get() = _product

    fun getProduct(page: Int = 1) {
        viewModelScope.launch {
            getProduct.invoke(page)
                .catch { error ->
                    _productDetails.value = UiEvents.Error(error)
                }
                .onStart {
                    _productDetails.value = UiEvents.Loading

                }
                .collectLatest {
                    _productDetails.value = UiEvents.Success(it)
                }
        }
    }
    fun getProductById(id: String) {
        viewModelScope.launch {
            getProductById.invoke(id)

                .catch { error ->
                    _product.value = UiEvents.Error(error)
                }
                .onStart {
                    _product.value = UiEvents.Loading
                }
                .collectLatest {
                    _product.value = UiEvents.Success(it)
                }
        }
    }
}