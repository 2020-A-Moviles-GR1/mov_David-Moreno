package com.example.johnny2.ui.cliente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.johnny2.R
import com.example.johnny2.ui.factura.FacturaActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_cliente.*

import kotlin.properties.Delegates


class AgregarClienteFragment : Fragment() {

    private lateinit var agregarusuarioViewModel: AgregarClienteViewModel
    private val db= FirebaseFirestore.getInstance()
    private lateinit var c:String
    private lateinit var nombre: TextView
    private lateinit var correo: TextView
    private lateinit var telefono: TextView
    private lateinit var ci: TextView
    private lateinit var direccion: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agregarusuarioViewModel =
            ViewModelProviders.of(this).get(AgregarClienteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cliente, container, false)
        val textView: TextView = root.findViewById(R.id.text_agregarcliente)
        agregarusuarioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        //obtencion de datos textview
        nombre = root.findViewById(R.id.txtNombreCliente)
        correo= root.findViewById(R.id.txtCorreoCliente)
        telefono=root.findViewById(R.id.txttelefonoCliente)
        ci=root.findViewById(R.id.txtCiCliente)
        direccion=root.findViewById(R.id.txtDireccionCliente)

        return root

    }//fin oncreateView




    override fun onStart() {
        super.onStart()
        btnagregarCliente.setOnClickListener {
            //obtencion de datos
            c=ci.text.toString()
            var nom=nombre.text.toString()
            var tel=telefono.text.toString()
            var corr=correo.text.toString()
            var dir=direccion.text.toString()

            println("DATOS CLIENTE PARA REGISTRO "+ c+" "+nom+" "+tel+" "+corr+" "+dir+" ")

            db.collection("cliente").document(c).set(
                hashMapOf("Cedula" to ci.text.toString() ,"Nombre" to nombre.text.toString(),"Telefono" to telefono.text.toString(),
                    "Correo" to correo.text.toString(), "Direccion" to direccion.text.toString() ) as Map<String, Any>)

        }//fin boton agregar
    }//fin OnStart





}
