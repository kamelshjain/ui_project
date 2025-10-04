package com.example.ui.Repositary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ui.Domain.BannerModel
import com.example.ui.Domain.ItemsModel
import com.example.ui.Domain.categoryModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepositary {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        val listdata = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BannerModel::class.java)
                    item?.let { list.add(it) }

                }
                listdata.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listdata
    }


//Connects to the Firebase Realtime Database.

//Listens to changes under the "Banner" node.

//Converts the data into a list of BannerModel objects.

//Emits the data using LiveData, allowing the UI to update reactively.

    fun loadCategory(): LiveData<MutableList<categoryModel>> {
        val listdata = MutableLiveData<MutableList<categoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<categoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(categoryModel::class.java)
                    item?.let { list.add(it) }

                }
                listdata.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listdata
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>> {
        val listdata = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Popular")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }

                }
                listdata.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listdata
    }

    fun loadItemCetegory(categoryId:String): LiveData<MutableList<ItemsModel>>{
        val itemsListData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Items")
        val query:Query=ref.orderByChild("categoryId").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for(childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                itemsListData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return itemsListData
    }
}