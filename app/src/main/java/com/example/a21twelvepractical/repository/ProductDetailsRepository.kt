package com.example.a21twelvepractical.repository

import com.example.a21twelvepractical.model.ProductsDetailsDataClass
import com.example.a21twelvepractical.retrofitService.ApiClient
import retrofit2.Response

class ProductDetailsRepository {

    private var apiClient: ApiClient = ApiClient()

    suspend fun getProductDetails():Response<ProductsDetailsDataClass>{
           return apiClient.getProductDetails()
    }
}