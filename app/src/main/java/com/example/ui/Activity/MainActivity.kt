package com.example.ui.Activity

import CategoryAdapter
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.Adapter.PopularAdapter

import com.example.ui.databinding.ActivityMainBinding
import com.example.ui.ViewModel.MainViewsModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewsModel = MainViewsModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         initBanner()
        initCategory()
        initPopular()

    }
    private fun initPopular() {
        binding.popularprogressbar.visibility = View.VISIBLE
        viewsModel.loadPopular().observeForever{
            binding.recyclerViewpopular.layoutManager=GridLayoutManager(this,2)
            binding.recyclerViewpopular.adapter=PopularAdapter(it)
            binding.popularprogressbar.visibility = View.GONE
        }
        viewsModel.loadPopular()
    }

    private fun initCategory() {
        binding.categoryprogressbar.visibility = View.VISIBLE
        viewsModel.loadCategory().observeForever{
            binding.categoryview.layoutManager=LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            binding.categoryview.adapter=CategoryAdapter(it)
            binding.categoryprogressbar.visibility = View.GONE
        }
        viewsModel.loadCategory()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewsModel.loadBanner().observeForever{
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)
            binding.progressBarBanner.visibility = View.GONE
        }
        viewsModel.loadBanner()
    }
}