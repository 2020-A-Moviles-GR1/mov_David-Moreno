package com.example.johnny2.ui.producto

class ProductoFactura (val codProducto:String, val nombreProducto:String,val precioPublico: String,val marca: String, val cantidad: String,val total: String) {

    override fun toString():String
    {
        return "${this.codProducto} ${this.nombreProducto} ${this.precioPublico}  ${this.marca}  ${this.cantidad}  ${this.total}"
    }


}