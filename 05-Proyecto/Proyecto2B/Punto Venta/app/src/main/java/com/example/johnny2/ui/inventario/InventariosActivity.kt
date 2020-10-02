package com.example.johnny2.ui.inventario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.johnny2.R
import com.example.johnny2.ui.base.BDD
import com.example.johnny2.ui.cliente.Cliente
import com.example.johnny2.ui.factura.FacturaActivity
import com.example.johnny2.ui.producto.Producto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_factura.*
import kotlinx.android.synthetic.main.activity_inventarios.*

class InventariosActivity : AppCompatActivity() {


    private var  listaInventarioProd= arrayListOf<Producto>()
    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventarios)

        btn_buscarInventario.setOnClickListener {
            db.collection("producto").get().addOnCompleteListener {
                for(documentos in it.result!!){
                    val cod=documentos.getString("Codigo")
                    val nombreP=documentos.getString("NomPro")
                    val precioProved=documentos.getString("PrecioPv")
                    val precioPub=documentos.getString("PVP")
                    val marca=documentos.getString("Marca")
                    val cantid=documentos.getString("Cantidad")
                    println("datos consultados: "+cod+" "+nombreP+" "+precioPub+" "+precioProved+" "+marca+" "+cantid)
                    llenarListaInventario(cod.toString(),nombreP.toString(),precioPub.toString(),precioProved.toString(),marca.toString(),cantid.toString())

                }
            }//fin db

        }


    }//fin OnCreate


    private fun llenarListaInventario(codProducto:String, nombreProducto:String, precioPublico: String,precioProv: String, marca: String, cantidad: String){

        listaInventarioProd.add(Producto(codProducto,nombreProducto,precioPublico,precioProv,marca,cantidad))
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInventarioProd)
        lv_listainventarioProd.adapter=adaptador
        lv_listainventarioProd.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptador.notifyDataSetChanged()
        }

        productolista()


    }
    private fun productolista(){
        lv_listainventarioProd.setOnItemClickListener { parent, view, position:Int, id ->
            val intent = Intent(this, FacturaActivity::class.java)
            //mensaje(position.toString())
            println(position.toString())
            val producto=lv_listainventarioProd.getItemAtPosition(position.toString().toInt())
            mensaje(producto.toString())
            println(producto.toString())
            intent.putExtra("producto",producto.toString())
            //BDD.anadirProductoFactura(producto.toString())
            intent.putExtra("posicion",position.toString())
            startActivity(intent)
        }//fin
    }

    private fun mensaje(message:String)
    {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Seleccion:")
        builder.setMessage(message)
        val dialog=builder.create()
        dialog.show()

    }





}