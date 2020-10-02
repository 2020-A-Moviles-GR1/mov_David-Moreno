package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_peliculas.*

class MainActivity : AppCompatActivity() {

    private lateinit var dato: TextView
    private var  listaGeneroPeli= arrayListOf<GeneroPeliculas>()
    private var contCodigo=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dato=findViewById(R.id.txt_dato)


        btn_limpiar.setOnClickListener{
            listaGeneroPeli.clear()
            listaGeneroPeli.forEach{
                println("luego del limpiar"+it)
            }
            val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaGeneroPeli)
            lv_generos.adapter=adaptador
            adaptador.notifyDataSetChanged()
            println("se borro")
            contCodigo=0

        }

        btn_buscar.setOnClickListener{

            for(i in listaGeneroPeli){
                println("\n")
                println(i)
                val strs=i.toString()
                val cadena= strs.split(" ").toTypedArray()
                if(dato.text.toString().equals(cadena[1])){
                    println("se eencontro el dato> "+cadena[1])
                    mensaje("El item buscado esta en: "+cadena[0].toString())
                }
            }
        }

        btn_agregar.setOnClickListener{

            // val editar=prefs.edit()
            contCodigo++
            llenarLista(contCodigo, dato.text.toString())
           // editar.putString(numero.text.toString(), dato.text.toString())
           // editar.apply()
            Log.i(contCodigo.toString(), dato.text.toString())
            mensaje(contCodigo.toString()+ dato.text.toString()+" se agrego")
           // peliculaslista()
            dato.setText("")
        }

        btn_eliminar.setOnClickListener{

            borrar()
            dato.setText("")
        }

        btn_cargar.setOnClickListener{
            llenadoAutomaticoGenero()

        }


    }// fin Oncreate


    private fun llenadoAutomaticoGenero(){
        llenarLista(1,"Terror")
        llenarLista(2,"Comedia")
        llenarLista(3,"Romantica")
        llenarLista(4,"Drama")
        llenarLista(5,"Animacion")
        contCodigo=5
    }

    private fun llenarLista(numero:Int, dato:String){

        listaGeneroPeli.add(GeneroPeliculas(numero,dato))
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaGeneroPeli)
        lv_generos.adapter=adaptador
        lv_generos.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptador.notifyDataSetChanged()
        }
        //se aniade al objeto en BDD
        Bdd.anadirGenero(numero,dato)
       peliculaslista()

    }

        private fun peliculaslista(){
            lv_generos.setOnItemClickListener { parent, view, position:Int, id ->
                val intent = Intent(this, PeliculasActivity::class.java)
                mensaje(position.toString())
                val nombreGenero=lv_generos.getItemAtPosition(position.toString().toInt())
                mensaje(nombreGenero.toString())
                intent.putExtra("genero",nombreGenero.toString())
                intent.putExtra("posicion",position.toString())
                startActivity(intent)}

        }
        private fun borrar(){
            var cont=0
            var aux=0

            for(i in listaGeneroPeli){

                println("parar borrar: "+i)
                val strs=i.toString()
                val cadena= strs.split(" ").toTypedArray()
                cont++
                println("se va a borrar "+cont+cadena[1])
                println("se va a borrar si es igual a "+dato.text.toString())
                if(dato.text.toString().equals(cadena[1]))
                {
                    aux=cont
                    println("se va a borrar aux "+aux+"contador"+cont+cadena[1])
                    println("se va a borrar si es igual a "+dato.text.toString())
                }
            }
            println("se eencontro el dato> "+aux)
            println("posicion "+(aux).toString())
            mensaje("El item a borrares: "+aux)
            listaGeneroPeli.removeAt(aux-1)

            listaGeneroPeli.forEach{
                println("nueva lista luego de borrar"+it)
            }
            val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaGeneroPeli)
            lv_generos.adapter=adaptador
            adaptador.notifyDataSetChanged()
        }

    private fun mensaje(message:String)
    {
        val builder=AlertDialog.Builder(this)
        builder.setTitle("mensaje:")
        builder.setMessage(message)
        val dialog=builder.create()
        dialog.show()

    }

    private fun recuperar() {
        println("estoy en recuperar")
        listaGeneroPeli = Bdd.listaGeneros
        listaGeneroPeli.forEach {
            println("recuperado: " + it)
        }
        val adaptadorRecuperado= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaGeneroPeli)
        lv_generos.adapter=adaptadorRecuperado
        lv_generos.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptadorRecuperado.notifyDataSetChanged()
        }


    }


    //acciones para guardar datos
    override fun onStart() {
        super.onStart()
        Log.i("Activity", "OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity", "OnRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity", "OnDestroy")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("activity", "onRestoreInstanceState")

        recuperar()
    }










}// fin clase
