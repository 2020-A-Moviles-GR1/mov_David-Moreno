package com.example.restaurante.clases

class PlatoHttp(
    var id: Int,
    var createdAt: String,
    var updatedAt: String,
    var nombre: String,
    var precioUnitario: Float,
    var urlImagen: String,
    var url: String,
    var latitud: String,
    var longitud: String
) {}

class CrearPlatoHttp(
    var nombre: String,
    var precioUnitario: Float,
    var urlImagen: String,
    var url: String,
    var latitud: Float,
    var longitud: Float
) {}