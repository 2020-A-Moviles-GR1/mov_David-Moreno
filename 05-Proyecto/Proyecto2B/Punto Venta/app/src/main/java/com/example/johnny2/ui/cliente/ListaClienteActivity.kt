package com.example.johnny2.ui.cliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.johnny2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_lista_cliente.*

class ListaClienteActivity : AppCompatActivity() {


    private var  listaCliente= arrayListOf<Cliente>()
    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cliente)

        btnmostrarClientes.setOnClickListener {
            db.collection("cliente").get().addOnCompleteListener {
                for(documentos in it.result!!){
                    val ced=documentos.getString("Cedula")
                    val nombre=documentos.getString("Nombre")
                    val correo=documentos.getString("Correo")
                    val direccion=documentos.getString("Direccion")
                    val telefono=documentos.getString("Telefono")
                    println("datos consultados: "+ced+" "+nombre+" "+correo+" "+direccion+" "+telefono)
                    llenarCliente(ced.toString(),nombre.toString(),correo.toString(),direccion.toString(),telefono.toString())
                }
            }//fin db
        }
    }

    private fun llenarCliente(cedulaIdentidadCliente:String, nombresCliente:String, correoCliente: String,direccionDomicilio: String, telefonoCelular:String){
        listaCliente.add(Cliente(cedulaIdentidadCliente,nombresCliente,telefonoCelular,correoCliente,direccionDomicilio))
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaCliente)
        lv_listaClientes.adapter=adaptador
        lv_listaClientes.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptador.notifyDataSetChanged()
        }


    }




}//fin clase