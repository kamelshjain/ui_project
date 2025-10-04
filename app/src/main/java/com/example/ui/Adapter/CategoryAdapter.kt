import android.icu.text.Transliterator.Position
import android.os.Handler
import android.os.Looper
import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.ui.Activity.ItemsListActivity
import com.example.ui.Domain.categoryModel
import com.example.ui.R

import com.example.ui.databinding.ViewholdeCategoryBinding


class CategoryAdapter(val items: MutableList<categoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
        private lateinit var context: Context
        private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding:ViewholdeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):CategoryAdapter.ViewHolder{
        context=parent.context
        val binding = ViewholdeCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder,position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title
        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent= Intent(context, ItemsListActivity::class.java).apply{
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                context.startActivity(intent, null)

            },500)

        }
        if(selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_full_cornrer_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_full_cornrer_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.dark_brown))
        }

    }

    override fun getItemCount(): Int =items.size
}