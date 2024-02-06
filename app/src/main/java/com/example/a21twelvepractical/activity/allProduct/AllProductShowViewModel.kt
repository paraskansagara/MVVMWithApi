package com.example.a21twelvepractical.activity.allProduct

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.example.a21twelvepractical.activity.allProduct.adapter.ProductListAdapter
import com.example.a21twelvepractical.databinding.ActivityProductDetailsBinding
import com.example.a21twelvepractical.databinding.ActivityProductShowBinding
import com.example.a21twelvepractical.model.ProductsDetailsDataClass
import com.example.a21twelvepractical.repository.ProductDetailsRepository
import kotlinx.coroutines.launch

class AllProductShowViewModel : ViewModel() {
    private var productDetailsRepository: ProductDetailsRepository = ProductDetailsRepository()

    val context: ObservableField<Context> = ObservableField()


    private val _getAllDataProductResponse: MutableLiveData<ProductsDetailsDataClass> =
        MutableLiveData()

    val getAllDataProductResponse: LiveData<ProductsDetailsDataClass>
        get() {
            return _getAllDataProductResponse
        }

    fun getProductAllDataApiCall() {
        viewModelScope.launch {
            val response = productDetailsRepository.getProductDetails()
            if (response.isSuccessful) {
                _getAllDataProductResponse.postValue(response.body())
            }
        }
    }

    fun setDataInRecyclerView(
        products: ArrayList<ProductsDetailsDataClass.Product>?,
        binding: ActivityProductShowBinding
    ) {

        if (products.isNullOrEmpty()) {
            binding.rvProductShow.isVisible = false
            binding.tvNoDataFound.isVisible = true
        } else {
            binding.rvProductShow.isVisible = true
            binding.tvNoDataFound.isVisible = false

            val adapter = ProductListAdapter(context.get()!!, products) {
                productDetailsDialog(it)
            }
            binding.rvProductShow.adapter = adapter
            swipeToDelete(adapter,products,binding)
        }
    }

    private fun swipeToDelete(
        adapter: ProductListAdapter,
        products: ArrayList<ProductsDetailsDataClass.Product>,
        binding: ActivityProductShowBinding,

        ) {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                products.removeAt(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                if (products.isEmpty()){
                    binding.rvProductShow.isVisible = false
                    binding.tvNoDataFound.isVisible = true
                }

            }
        }).attachToRecyclerView(binding.rvProductShow)
    }


    private fun productDetailsDialog(product: ProductsDetailsDataClass.Product) {
        val dialog = Dialog(context.get()!!)
        val dialogBinding = ActivityProductDetailsBinding.inflate(
            LayoutInflater.from(context.get()!!), null, false
        )
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)
        val imageList = ArrayList<SlideUIModel>()
        product.images?.map {
            imageList.add(SlideUIModel(it,""))
        }
        dialogBinding.imageSlide.setImageList(imageList)

        dialogBinding.tvProductDetailsShow.text = buildString {
            append("Product Name :")
            append(product.title)
            append("\n")
            append("Brand :")
            append(product.brand)
            append("\n")
            append("Price :")
            append(product.price)
            append("\n")
            append("Rating :")
            append(product.rating)
            append("\n")
            append("Stock :")
            append(product.stock)
            append("\n")
            append("Category :")
            append(product.category)
            append("\n")
            append("Description :")
            append(product.description)
            append("\n")
            append("DiscountPercentage :")
            append(product.discountPercentage)
        }

        dialog.show()

    }

}