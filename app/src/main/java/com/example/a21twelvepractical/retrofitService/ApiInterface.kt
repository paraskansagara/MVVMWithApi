package com.example.a21twelvepractical.retrofitService

import com.example.a21twelvepractical.model.ProductsDetailsDataClass
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    suspend fun getProductDetails(): Response<ProductsDetailsDataClass>
}