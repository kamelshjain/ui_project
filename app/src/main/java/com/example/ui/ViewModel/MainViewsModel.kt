package com.example.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ui.Domain.BannerModel
import com.example.ui.Domain.ItemsModel
import com.example.ui.Domain.categoryModel
import com.example.ui.Repositary.MainRepositary

class MainViewsModel: ViewModel() {
    private val repositary=MainRepositary()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repositary.loadBanner()
    }
    fun loadCategory():LiveData<MutableList<categoryModel>>{
        return repositary.loadCategory()
    }
    fun loadPopular():LiveData<MutableList<ItemsModel>>{
        return repositary.loadPopular()
    }
    fun loadItems(categoryId:String):LiveData<MutableList<ItemsModel>>{
        return repositary.loadItemCetegory(categoryId)
    }
    }
