package com.example.ui.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ui.Domain.ItemsModel
import com.example.ui.Helper.ManagmentCart
import com.example.ui.R
import com.example.ui.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)
        bundle()

    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            discriptionTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingTxt.text = item.rating.toString()
            addTOcart.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberincart.text.toString()
                )
                managementCart.insertItems(item)
            }

            backBtn.setOnClickListener { finish() }
            plus.setOnClickListener {
                numberincart.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }
            minus.setOnClickListener {
                if (item.numberInCart > 0) {
                    numberincart.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}