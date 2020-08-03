package com.example.examen

import android.util.Log

class Bdd {
    companion object {
         var listaPeliculas= arrayListOf<Peliculas>()
        var listaGeneros=arrayListOf<GeneroPeliculas>()

        fun anadirPelicula(a1:String,a2:String,a3: String) {

            listaPeliculas.add(Peliculas(a1,a2,a3))
            Log.i("se ingreso pelicula",a1+a2+a3)
            println("se agrego: "+a1+a2+a3)
        }
        fun anadirGenero(a1:Int,a2:String) {

            listaGeneros.add(GeneroPeliculas(a1,a2))
            Log.i("se ingreso genero",a1.toString()+a2)
            println("se agrego: "+a1.toString()+a2)
        }

    }
}