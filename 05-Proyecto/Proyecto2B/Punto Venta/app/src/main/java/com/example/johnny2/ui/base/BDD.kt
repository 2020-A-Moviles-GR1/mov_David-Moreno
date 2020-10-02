package com.example.johnny2.ui.base

import android.util.Log

import com.example.johnny2.ui.producto.ProductoFactura

class BDD {
    companion object {
        var listaProductosFactura= arrayListOf<ProductoFactura>()
        var listaprecios= arrayListOf<Double>()
        val listaventas= arrayListOf<Double>()


        fun anadirProductoFactura(a1:String,a2:String,a3:String,a4:String,a5:String,a6:String) {

            listaProductosFactura.add(ProductoFactura(a1,a2,a3,a4,a5,a6))
            Log.i("se ingreso producto",a1)
            println("se agrego: "+a1)
        }

        fun anadirPrecios(numero:Double)
        {
            listaprecios.add(numero)

        }

        fun anadirVenta(venta:Double){
            listaventas.add(venta)
        }


    }



}