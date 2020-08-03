package com.example.examen

class Peliculas(var generoP:String, var nombre:String, var fechaEstreno: String) {




    override fun toString():String
    {
        return "${this.generoP} ${this.nombre} ${this.fechaEstreno}"
    }





}//fin class