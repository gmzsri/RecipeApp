package com.example.tasarimcalismasi.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasarimcalismasi.Model.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel():ViewModel() {
    private val firebaseDatabase=FirebaseDatabase.getInstance()
    private val _category= MutableLiveData<MutableList<CategoryModel>>()

    val category: LiveData<MutableList<CategoryModel>> = _category

    fun loadCategory(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<CategoryModel>()
                  for(child in snapshot.children){
                      val data=child.getValue(CategoryModel::class.java)
                      if(data!=null){
                          list.add(data)
                      }
                  }
                _category.value=list
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }







}