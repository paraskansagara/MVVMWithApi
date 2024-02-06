package com.example.a21twelvepractical.activity.allProduct.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.bumptech.glide.Glide
import com.example.a21twelvepractical.R
import com.example.a21twelvepractical.databinding.ItemListProductDetailsBinding
import com.example.a21twelvepractical.model.ProductsDetailsDataClass
import java.util.ArrayList

class ProductListAdapter(
    val context: Context,
    val products: ArrayList<ProductsDetailsDataClass.Product>,
    val onClickListener: (ProductsDetailsDataClass.Product) -> Unit
):RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(ItemListProductDetailsBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
       holder.setValue()
    }

    override fun getItemCount() = products.size


    inner class ProductListViewHolder(val binding: ItemListProductDetailsBinding) :RecyclerView.ViewHolder(binding.root){
        fun setValue() {
          val data = products[adapterPosition]
            val imageList = ArrayList<SlideUIModel>()


            binding.root.setOnClickListener {
                onClickListener.invoke(data)
            }



            Glide.with(context).load(data.thumbnail).placeholder(R.drawable.ic_launcher_background).into(binding.ivProductThumbnail)
            binding.tvItemListProductDetailsShow.text = buildString {
                append("Product Name :")
                append(data.title)
                append("\n")
                append("Brand :")
                append(data.brand)
            }

            data.images?.map {
                imageList.add(SlideUIModel(it,""))
            }
            binding.imageSlide.setImageList(imageList)

        }

    }
}