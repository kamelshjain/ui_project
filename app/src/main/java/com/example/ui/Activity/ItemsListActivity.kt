package com.example.ui.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ui.Adapter.ItemListAdapter
import com.example.ui.ViewModel.MainViewsModel
import com.example.ui.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemsListBinding
    private var viewModel = MainViewsModel()
    private var id:String=""
    private var title:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundles()
        initList()
    }
    private fun getBundles() {
        id=intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!
        binding.categoryText.text=title
    }
    private fun initList() {
        binding.apply {
            progressBar.visibility= View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer {
                listView.layoutManager=
                    GridLayoutManager(this@ItemsListActivity,2)
                listView.adapter= ItemListAdapter(it)
                progressBar.visibility=View.GONE
            })
            Backbtn.setOnClickListener {finish()}
            }

    }
}