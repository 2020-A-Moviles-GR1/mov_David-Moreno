package com.example.johnny2.ui.agregar_usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgregarusuarioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is  agregar usuario"
    }
    val text: LiveData<String> = _text
}