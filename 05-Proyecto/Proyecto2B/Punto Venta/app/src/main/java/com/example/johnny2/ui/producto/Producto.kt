package com.example.johnny2.ui.producto

class Producto (val codProducto:String, val nombreProducto:String,val precioPublico: String,val precioProv: String,val marca: String, val cantidad: String) {

        override fun toString():String
        {
            return "${this.codProducto} ${this.nombreProducto} ${this.precioPublico} ${this.precioProv} ${this.marca}  ${this.cantidad}"
        }


    }