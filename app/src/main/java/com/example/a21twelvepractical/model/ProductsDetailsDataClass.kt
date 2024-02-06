package com.example.a21twelvepractical.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable
import java.util.ArrayList

@Keep
data class ProductsDetailsDataClass(
    @SerializedName("limit")
    val limit: Int?, // 30
    @SerializedName("products")
    val products: ArrayList<Product>?,
    @SerializedName("skip")
    val skip: Int?, // 0
    @SerializedName("total")
    val total: Int? // 100
) {
    @Keep
    data class Product(
        @SerializedName("brand")
        val brand: String?, // Apple
        @SerializedName("category")
        val category: String?, // smartphones
        @SerializedName("description")
        val description: String?, // An apple mobile which is nothing like apple
        @SerializedName("discountPercentage")
        val discountPercentage: Double?, // 12.96
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("images")
        val images: List<String>?,
        @SerializedName("price")
        val price: Int?, // 549
        @SerializedName("rating")
        val rating: Double?, // 4.69
        @SerializedName("stock")
        val stock: Int?, // 94
        @SerializedName("thumbnail")
        val thumbnail: String?, // https://cdn.dummyjson.com/product-images/1/thumbnail.jpg
        @SerializedName("title")
        val title: String? // iPhone 9
    ):Serializable
}