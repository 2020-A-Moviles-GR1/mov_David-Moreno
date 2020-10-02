package com.example.johnny2.ui.ingreso_producto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngresoProductoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ingrese el producto"
    }
    val text: LiveData<String> = _text
}