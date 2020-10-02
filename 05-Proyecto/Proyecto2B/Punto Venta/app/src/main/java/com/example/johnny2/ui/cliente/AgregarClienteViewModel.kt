package com.example.johnny2.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgregarClienteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is  agregar Cliente"
    }
    val text: LiveData<String> = _text
}