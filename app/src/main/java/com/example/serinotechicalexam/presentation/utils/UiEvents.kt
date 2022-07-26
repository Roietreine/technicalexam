package com.example.serinotechicalexam.presentation.utils

sealed class UiEvents<out T>{

    data class Success<out T>(val data : T) : UiEvents<T>()
    data class Error(val error : Throwable) : UiEvents<Nothing>()
    object Loading : UiEvents<Nothing>()
}
