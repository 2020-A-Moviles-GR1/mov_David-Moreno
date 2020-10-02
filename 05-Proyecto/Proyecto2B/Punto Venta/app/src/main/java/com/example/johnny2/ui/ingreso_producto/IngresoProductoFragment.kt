package com.example.johnny2.ui.ingreso_producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.johnny2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_ingreso_producto.*

class IngresoProductoFragment : Fragment() {
    private val db= FirebaseFirestore.getInstance()
    private lateinit var ingresoProductoViewModel: IngresoProductoViewModel
    private lateinit var codigoProducto: TextView
    private lateinit var nombreProducto: TextView
    private lateinit var precioVenta: TextView
    private lateinit var precioProveedor: TextView
    private lateinit var marca: TextView
    private lateinit var cantidad: TextView
    private lateinit var c:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ingresoProductoViewModel =
            ViewModelProviders.of(this).get(IngresoProductoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ingreso_producto, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        ingresoProductoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        //obtencion de datos textview
        codigoProducto = root.findViewById(R.id.CodProducto)
        nombreProducto= root.findViewById(R.id.Nombre_Producto)
        precioVenta=root.findViewById(R.id.NPrecioVenta)
        precioProveedor=root.findViewById(R.id.NprecioProv)
        marca=root.findViewById(R.id.txtmarca)
        cantidad=root.findViewById(R.id.Ncantidad)




        return root
    }

    override fun onStart() {
        super.onStart()
        btnIngresoProducto.setOnClickListener {
            //obtencion de datos
            c=codigoProducto.text.toString()
            var nom=nombreProducto.text.toString()
            var preVe=precioVenta.text.toString()
            var prPr=precioProveedor.text.toString()
            var mar=marca.text.toString()
            var cant=cantidad.text.toString()

            println("DATOS PRODUCTO PARA REGISTRO "+ c+" "+nom+" "+preVe+" "+prPr+" "+mar+" "+cant)

            db.collection("producto").document(c).set(
                hashMapOf("Codigo" to codigoProducto.text.toString() ,"NomPro" to nombreProducto.text.toString(),"PVP" to precioVenta.text.toString(),
                    "PrecioPv" to precioProveedor.text.toString(), "Marca" to marca.text.toString(), "Cantidad" to cantidad.text.toString()) as Map<String, Any>)


        }//fin boton agregar
    }//fin OnStart



}//fin frag
