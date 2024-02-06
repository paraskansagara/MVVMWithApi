package com.example.a21twelvepractical.activity.allProduct

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.a21twelvepractical.R
import com.example.a21twelvepractical.common.DataBindingActivity
import com.example.a21twelvepractical.common.ViewModelFactory
import com.example.a21twelvepractical.databinding.ActivityProductShowBinding

class AllProductShowActivity : DataBindingActivity() {
    private val binding: ActivityProductShowBinding by binding(R.layout.activity_product_show)
    private lateinit var viewModel: AllProductShowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, ViewModelFactory())[AllProductShowViewModel::class.java]
        viewModel.context.set(this)
        binding.progressCircular.isVisible = true
        viewModel.getProductAllDataApiCall()
        apiDataObserver()
    }


    private fun apiDataObserver() {
        viewModel.getAllDataProductResponse.observe(this) {
            viewModel.setDataInRecyclerView(it.products,binding)
            binding.progressCircular.isVisible = false
        }
    }
}