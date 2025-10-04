package com.example.ui.Domain
import java.io.Serializable

data class ItemsModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0, // This is the one you should use
    var extra: String = ""
) : Serializable