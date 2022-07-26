package com.example.serinotechicalexam.data.dto

import com.google.gson.annotations.SerializedName

data class Product (

    val id : String,
    val title : String,
    val description : String,
    val price : String,

    @SerializedName("discountPercentage")
    val discount : Double,
    val rating : Double,
    val stock : Int,
    val brand : String,
    val category : String,
    val thumbnail : String,
    val images : List<String>,






)
