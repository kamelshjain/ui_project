package com.example.ui.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ui.Activity.DetailActivity
import com.example.ui.Domain.ItemsModel
import com.example.ui.databinding.ViewholderItemListBinding
import com.example.ui.databinding.ViewholderPopularBinding

class ItemListAdapter(val items:MutableList<ItemsModel>):
RecyclerView.Adapter<ItemListAdapter.Viewholder>(){
    
    lateinit var context: Context
    
    class Viewholder(val binding: ViewholderItemListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListAdapter.Viewholder {
        context=parent.context
        val binding=
            ViewholderItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemListAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$" + items[position].price.toString()
        holder.binding.subtitleTxt.text = items[position].extra.toString()

        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int = items.size
}