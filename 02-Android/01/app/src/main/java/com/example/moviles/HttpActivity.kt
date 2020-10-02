package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.56.1:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        btn_obtener
            .setOnClickListener {
                obtenerUsuarios()
            }

        btn_crear
            .setOnClickListener {
                crearUsuario()
            }
        btn_usuario_conver
            .setOnClickListener {
                convertirUsuario()
            }

        btn_pokemon_conv
            .setOnClickListener {
                convertirPokemon()
            }

    }

    fun crearUsuario() {
        val url = urlPrincipal + "/Usuario"

        val parametrosUsuario: List<Pair<String, String>> = listOf(
            "cedula" to "1122334466",
            "nombre" to "Ramiro",
            "correo" to "r@r.com",
            "estadoCivil" to "Casado",
            "password" to "A123456789b"
        )

        url.httpPost(parametrosUsuario).responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaon", "${usuarioString}")
                    }
                }
            }
    }

    fun obtenerUsuarios() {
        val pokemonString = """
            {
       "createdAt": 1600733805837,
    "updatedAt": 1600733805837,
    "id": 1,
    "nombre": "charmander",
    "usuario":1
          }
        """.trimIndent()

        val pokemonInstancia = Klaxon()
            .parse<PokemonHttp>(pokemonString)

        if (pokemonInstancia != null) {
            Log.i("http-klaxon", "Nombre: ${pokemonInstancia.nombre}")
            Log.i(
                "http-klaxon",
                "Nombre: ${pokemonInstancia.fechaCreacion}"
            )
        }


        val url = urlPrincipal + "/Usuario"
        url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-klaxon", "Data: ${data}")

                        val usuarios = Klaxon()
                            .parseArray<UsuarioHttp>(data)
                        usuarios?.forEach {
                            Log.i( "http-klaxon", "Nombre: ${it.nombre}"  + " Estado civil: ${it.estadoCivil}")
                            if (it.pokemons!!.size > 0) {
                                it.pokemons!!.forEach {
                                    Log.i( "http-klaxon","Nombre: ${it.nombre}"
                                    )
                                }
                            }
                        }


                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon", "Error: ${ex.message}")
                    }
                }
            }
    }

    fun convertirUsuario(){
        val url = urlPrincipal + "/Usuario"
        url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon", "Error Us: ${ex.message}")
                    }

                    is Result.Success -> {
                        val data = result.get()
                        val usuarios = Klaxon().converter(UsuarioHttp.converterUs).parseArray<UsuarioHttp>(data)
                        if (usuarios != null) {
                            usuarios.forEach {
                                Log.i("http-klaxon","$it")


                                    it.pokemons?.forEach {
                                        Log.i("http-klaxon","$it")
                                    }

                        }


                    }

                }
            }
    }
    }
    fun convertirPokemon(){
        val url = urlPrincipal + "/Pokemon"
        url.httpGet() .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon", "Error Pok: ${ex.message}")
                    }

                    is Result.Success -> {
                        val data = result.get()
                        val pokemon = Klaxon().converter(PokemonHttp.ConverterPk).parseArray<PokemonHttp>(data)
                        if (pokemon != null) {
                            pokemon.forEach {
                                Log.i( "http-klaxon", "$it")

                            }


                        }

                    }
                }
            }
    }
}