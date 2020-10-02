package com.example.johnny2.ui.factura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.johnny2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_factura.*

class DatosFacturaActivity : AppCompatActivity() {
    private val db= FirebaseFirestore.getInstance()
    private lateinit var c:String
    private lateinit var nombre: TextView
    private lateinit var correo: TextView
    private lateinit var telefono: TextView
    private lateinit var ci: TextView
    private lateinit var direccion: TextView
    private lateinit var fecha: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_factura)
        nombre = findViewById(R.id.txtNombreClienteFactura)
        correo = findViewById(R.id.txtEmailClienteFactura)
        telefono = findViewById(R.id.txtTelefonoFactura)
        ci = findViewById(R.id.txtCiFacturaCliente)
        direccion = findViewById(R.id.txtDireccionClienteFactura)
        fecha = findViewById(R.id.txtFechaCompra)

        btn_siguiente.setOnClickListener {
            val intent = Intent(this, FacturaActivity::class.java)
            startActivity(intent)
        }

        btnBuscarCi.setOnClickListener {
            db.collection("cliente").get().addOnCompleteListener {
                for (documentos in it.result!!) {
                    val ced = documentos.getString("Cedula")
                    println("la cedula ingresada es: " + ci.text.toString() + "y la cedula consutada es: " + ced)
                    if (ced.equals(ci.text.toString())) {
                        val nombre = documentos.getString("Nombre")
                        val correo = documentos.getString("Correo")
                        val direccion = documentos.getString("Direccion")
                        val telefono = documentos.getString("Telefono")
                        println("datos consulta factura: " + ced + " " + nombre + " " + correo + " " + direccion + " " + telefono)
                        txtNombreClienteFactura.setText(nombre)
                        txtEmailClienteFactura.setText(correo)
                        txtTelefonoFactura.setText(telefono)
                        txtDireccionClienteFactura.setText(direccion)
                    }//fin if
                }//fin for
            }//fin db
        }//fin boton

    }//fin onCreate



}