package com.example.a21twelvepractical.retrofitService

import com.example.a21twelvepractical.model.ProductsDetailsDataClass
import retrofit2.Response

class ApiClient {

    private val apiInitializer = ApiInitialize.initialize()


    suspend fun getProductDetails():Response<ProductsDetailsDataClass>{
       return apiInitializer.getProductDetails()
    }

}