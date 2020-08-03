package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_peliculas.*


class PeliculasActivity : AppCompatActivity() {

    private lateinit var titulo: TextView
    private var  listaPeliculas= arrayListOf<Peliculas>()
    private var contCodigo=0
    private var titulogeneroDeActivity=""
    private var posicionOtroActivity=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)
        Log.i("Activity", "OnCreate")

        titulo=findViewById(R.id.txt_titulo)
        //se agrega el nombre en la caja de texto
        //que se envia de otro activy
        val mensajeInten: Intent =intent
        val genero=mensajeInten.getStringExtra("genero")
        titulo.setText(genero)
        titulogeneroDeActivity=genero.toString()

        val posicionInten: Intent =intent
        val posicionListview=posicionInten.getStringExtra("posicion")
        println("esta es posicion desde el activiti anterior: "+posicionListview)

        val adaptador= ArrayAdapter(
            this,android.R.layout.simple_list_item_1, listaPeliculas)
        lv_peliculas.adapter=adaptador
        lv_peliculas.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
        }



        btn_eliminarPelicula.setOnClickListener{
        borrar()
            txtnombrePelicula.setText("")
            txtanioEstreno.setText("")

        }

        btn_agregarPelicula.setOnClickListener{

            // val editar=prefs.edit()
            contCodigo++
            val codigoPeli:String=codigoGenerador(contCodigo.toString())
            llenarLista(codigoPeli, txtnombrePelicula.text.toString(),txtanioEstreno.text.toString())
            Log.i("peliculas: ",codigoPeli+ txtnombrePelicula.text.toString()+txtanioEstreno.text.toString())
            mensaje(txtnombrePelicula.text.toString()+txtanioEstreno.text.toString()+" se agrego")
            // peliculaslista()
            txtnombrePelicula.setText("")
            txtanioEstreno.setText("")
        }
        btn_buscarPelicula.setOnClickListener{
            for(i in listaPeliculas){
                println("\n")
                println(i)
                val strs=i.toString()
                val cadena= strs.split(" ").toTypedArray()
                if(txtnombrePelicula.text.toString().equals(cadena[1])){
                    println("se eencontro el dato> "+cadena[1])
                    mensaje("El item buscado esta en: "+cadena[0].toString())
                }
            }

        }

        btn_limpiarPelicula.setOnClickListener{
            listaPeliculas.clear()
            listaPeliculas.forEach{
                println("luego del limpiar"+it)
            }
            val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPeliculas)
            lv_peliculas.adapter=adaptador
            adaptador.notifyDataSetChanged()
            println("se borro")
            txtnombrePelicula.setText("")
            txtanioEstreno.setText("")
            contCodigo=0
        }


        btn_cargarPelicula.setOnClickListener{
            llenadoAutomatico(posicionListview.toInt())

        }

    }//fin OnCreat


    private fun llenarLista(genero:String, nombre:String,fecha:String){

        listaPeliculas.add(Peliculas(genero,nombre,fecha))
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPeliculas)
        lv_peliculas.adapter=adaptador
        lv_peliculas.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            llenadoAutomatico(position)
            adaptador.notifyDataSetChanged()
        }
        //se aniade al objeto en BDD
        Bdd.anadirPelicula(genero,nombre,fecha)


    }
private fun codigoGenerador(codigo:String): String {
    val g=titulogeneroDeActivity
    val cadena= g.split(" ").toTypedArray()
    val a1=cadena[0]
    val a2=cadena[1]
    val a3=cadena[2]
    return codigo+a1+a2+a3
}


    private fun mensaje(message:String)
    {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("mensaje:")
        builder.setMessage(message)
        val dialog=builder.create()
        dialog.show()

    }


    private fun borrar(){
        var cont=0
        var aux=0

        for(i in listaPeliculas){

            println("parar borrar: "+i)
            val strs=i.toString()
            val cadena= strs.split(" ").toTypedArray()
            cont++
            println("se va a borrar "+cont+cadena[1])
            println("se va a borrar si es igual a "+this.txtnombrePelicula.text.toString())
            if(txtnombrePelicula.text.toString().equals(cadena[1]))
            {
                aux=cont
                println("se va a borrar aux "+aux+"contador"+cont+cadena[1])
                println("se va a borrar si es igual a "+txtnombrePelicula.text.toString())
            }
        }
        println("se eencontro el dato> "+aux)
        println("posicion "+(aux).toString())
        mensaje("El item a borrares: "+aux)
        listaPeliculas.removeAt(aux-1)

        listaPeliculas.forEach{
            println("nueva lista luego de borrar"+it)
        }
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPeliculas)
        lv_peliculas.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }


    private fun recuperar() {
        println("estoy en recuperar")
        listaPeliculas = Bdd.listaPeliculas
        listaPeliculas.forEach {
            println("recuperado: " + it)
        }
        val adaptadorRecuperado= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPeliculas)
        lv_peliculas.adapter=adaptadorRecuperado
        lv_peliculas.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptadorRecuperado.notifyDataSetChanged()
        }


    }


    private fun llenadoAutomatico(posicion:Int)
    {
        println(posicion)
        when(posicion)
        {
            0->peliTerror()
            1->peliComedia()
            2->peliRomanticas()
            3->listaPDrama()
            4->listaPAnimacion()

        }

    }

    private fun peliTerror(){
        llenarLista((codigoGenerador((1).toString())),"Anabelle","2014")
        llenarLista((codigoGenerador((2).toString())),"IT","2017")
        llenarLista((codigoGenerador((3).toString())),"Sleepy Hollow","1999")
        llenarLista((codigoGenerador((4).toString())),"La monja","2018")
        llenarLista((codigoGenerador((5).toString())),"Saw 5","2008")
        contCodigo=5
    }
    private fun peliRomanticas(){

        llenarLista((codigoGenerador((1).toString())),"A dos metros de ti","2019")
        llenarLista((codigoGenerador((2).toString())),"Bajo el sol de Toscana ","2003")
        llenarLista((codigoGenerador((3).toString())),"Titanic","1997")
        llenarLista((codigoGenerador((4).toString())),"Otoño en Nueva York","2000")
        llenarLista((codigoGenerador((5).toString())),"Tengo ganas de ti","2012")
        contCodigo=5
    }
    private fun peliComedia(){
        llenarLista((codigoGenerador((1).toString())),"Una noches en el museo","2006")
        llenarLista((codigoGenerador((2).toString())),"La era del hielo 2","2006")
        llenarLista((codigoGenerador((3).toString())),"Chicas pesadas","2004")
        llenarLista((codigoGenerador((4).toString())),"Espías a escondidas","2019")
        llenarLista((codigoGenerador((5).toString())),"La máscara","1994")
        contCodigo=5
    }
    private fun listaPDrama(){
        llenarLista(codigoGenerador((1).toString()),"Un don excepcional","2017")
        llenarLista(codigoGenerador((2).toString()),"El niño con el pijama de rayas","2008")
        llenarLista(codigoGenerador((3).toString()),"Una aventura extraordinaria","2012")
        llenarLista(codigoGenerador((4).toString()),"The lovely bones","2009")
        llenarLista(codigoGenerador((5).toString()),"8 mile","2002")
        contCodigo=5
    }
    private fun listaPAnimacion(){

        llenarLista((codigoGenerador((1).toString())),"Wall•E","2008")
        llenarLista((codigoGenerador((2).toString())),"Coco","2017")
        llenarLista((codigoGenerador((3).toString())),"Madagascar","2005")
        llenarLista((codigoGenerador((4).toString())),"Los pitufos en la aldea perdida","2017")
        llenarLista((codigoGenerador((5).toString())),"Río","2011")
        contCodigo=5

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






}//fin clase




