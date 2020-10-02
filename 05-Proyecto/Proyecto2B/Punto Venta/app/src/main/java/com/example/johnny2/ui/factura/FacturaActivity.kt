package com.example.johnny2.ui.factura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.johnny2.Barra_Menu_Activity
import com.example.johnny2.R
import com.example.johnny2.ui.base.BDD
import com.example.johnny2.ui.inventario.InventariosActivity
import com.example.johnny2.ui.producto.Producto
import com.example.johnny2.ui.producto.ProductoFactura
import kotlinx.android.synthetic.main.activity_factura.*

class FacturaActivity : AppCompatActivity() {
    private var  listaProductos= arrayListOf<ProductoFactura>()
    private var  token= arrayListOf<String>()
    private lateinit var cantidadFactura:TextView
    private var sumaPrecios=arrayListOf<Double>()
    private var totalF:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factura)
        cantidadFactura=findViewById(R.id.txtcantidadPrFactura)




        btnAgregarProducto.setOnClickListener {

            val mensajeInten: Intent =intent
            val producto=mensajeInten.getStringExtra("producto")
            println("el producto pasado es: "+producto.toString())

            token= producto.toString().split(" ") as ArrayList<String>
            token.forEach {
                println("tokenizado: " + it)
            }
            println("la cantidad de producto es: "+token[6])
            txtnombreProductoFactura.setText(token[0])

            if (cantidadFactura.text.toString().toInt() <= token[6].toInt()){
                var totalProducto=cantidadFactura.text.toString().toInt()*token[2].toDouble()
                BDD.anadirPrecios(totalProducto)
                println("precio total del producto "+totalProducto)
                llenarLista(token[0],token[1],token[2],token[4],cantidadFactura.text.toString(),totalProducto.toString())
            }

            println("el precio del producto seleccionado es: "+token[2])


           // mensaje("Se ha agregado el producto")

        }//fin boton



        btn_BuscarProducto.setOnClickListener {
            val intent = Intent(this, InventariosActivity::class.java)
            startActivity(intent)

        }

        btnpagar.setOnClickListener {
            val intent =Intent(this, Barra_Menu_Activity::class.java)
            intent.putExtra("valor",totalF)

            startActivity(intent)
            BDD.anadirVenta(totalF)


        }

        btnFacturar.setOnClickListener{
            listaProductos.clear()
            recuperar()
            mensaje("Se ha facturado")

            sumaPrecios=BDD.listaprecios
            var suma:Double=0.0
            sumaPrecios.forEach {
                suma+=it
                println("recuperado: " + it)
            }
            var iva:Double=0.0
            var a=suma/1.12
            iva=suma-a

            totalF=suma
            println("precio subtotal es:"+suma+" el iva es: "+iva)
            txtsubtotalFactura.setText(a.toString())
            txtIvaFactura.setText(iva.toString())
            txtTotalFactura.setText(totalF.toString())



        }



    }
    private fun mensaje(message:String)
    {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("mensaje:")
        builder.setMessage(message)
        val dialog=builder.create()
        dialog.show()

    }


    private fun llenarLista(a1:String,a2:String,a3:String,a4:String,a5:String,a6:String){

        listaProductos.add(ProductoFactura(a1,a2,a3,a4,a5,a6))
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaProductos)
        lv_listaProductosFacura.adapter=adaptador
        lv_listaProductosFacura.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptador.notifyDataSetChanged()
        }
        //se aniade al objeto en BDD
        BDD.anadirProductoFactura(a1,a2,a3,a4,a5,a6)
    }

    private fun recuperar(){

        println("estoy en recuperar")
        listaProductos = BDD.listaProductosFactura
        listaProductos.forEach {
            println("recuperado: " + it)
        }
        val adaptadorRecuperado= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaProductos)
        lv_listaProductosFacura.adapter=adaptadorRecuperado
        lv_listaProductosFacura.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id-> Log.i("list-view","Posicion $position")
            adaptadorRecuperado.notifyDataSetChanged()
        }


    }

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
        recuperar()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity", "OnDestroy")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("activity", "onRestoreInstanceState")






    }

}//fin calse