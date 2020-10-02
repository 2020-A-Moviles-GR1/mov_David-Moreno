package com.example.johnny2.ui.caja

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CajaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Inicio Caja"
    }
    val text: LiveData<String> = _text
}