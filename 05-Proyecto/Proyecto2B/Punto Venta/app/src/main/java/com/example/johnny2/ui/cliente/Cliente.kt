package com.example.johnny2.ui.cliente

class Cliente(val cedulaIdentidadCliente:String, val nombresCliente:String, val correoCliente: String,val direccionDomicilio: String, val telefonoCelular:String) {

    override fun toString():String
    {
        return "${this.cedulaIdentidadCliente} ${this.nombresCliente} ${this.telefonoCelular} ${this.correoCliente} ${this.direccionDomicilio}"
    }


}