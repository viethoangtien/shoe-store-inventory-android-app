package com.example.showstoreinventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showstoreinventory.model.Shoe

class MainActivityViewModel : ViewModel() {

    var shoe: Shoe = Shoe()

    val shoeList: MutableList<Shoe> = mutableListOf()

    private var _shoeListLiveData: MutableLiveData<List<Shoe>> = MutableLiveData(emptyList())
    var shoeListLiveData: LiveData<List<Shoe>> = _shoeListLiveData

    fun saveShoeDetail() {
        if (shoe.name.isNotEmpty() && shoe.company.isNotEmpty() && shoe.size > 0 && shoe.description.isNotEmpty()) {
            shoeList.add(shoe.copy())
            shoe = Shoe()
            _shoeListLiveData.value = shoeList
        }
    }
}