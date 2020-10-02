package com.example.moviles

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*

class PokemonHttp(
    var createdAt: Long,
    var updatedAt: Long,
    var id: Int,
    var nombre: String,
    var usuario: Any?
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "Pokemon: $nombre, ID: $id, Usuario: $usuario"
    }
    companion object{
        val ConverterPk = object: Converter{
            override fun canConvert(cls: Class<*>)= cls == PokemonHttp::class.java

            override fun fromJson(jv: JsonValue): PokemonHttp {
                return if (jv.obj?.get("usuario") is JsonObject){
                      PokemonHttp(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("nombre"),
                        Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj?.get("usuario") as JsonObject))
                }
                 else if (jv.obj?.get("usuario") is Int){
                    return   PokemonHttp( jv.objInt("createdAt").toLong(),  jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"), jv.objString("nombre"),
                        jv.objInt("usuario") )
                } else{
                    throw Error("Error")
                }
            }

            override fun toJson(value: Any): String {
                TODO("Not yet implemented")
            }

        }
        }
}