package com.example.johnny2.ui.agregar_usuario

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
import kotlinx.android.synthetic.main.fragment_agregarusuario.*
import kotlin.properties.Delegates


class AgregarusuarioFragment : Fragment() {

    private lateinit var agregarusuarioViewModel: AgregarusuarioViewModel
    private val db= FirebaseFirestore.getInstance()
    private lateinit var c:String
    private lateinit var nombre: TextView
    private lateinit var correo: TextView
    private lateinit var celular: TextView
    private lateinit var contrasenia1: TextView
    private lateinit var contrasenia2: TextView
    private lateinit var vendedor: RadioButton
    private lateinit var administrador: RadioButton
    private  lateinit var  RolEmpleado:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agregarusuarioViewModel =
            ViewModelProviders.of(this).get(AgregarusuarioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_agregarusuario, container, false)
        val textView: TextView = root.findViewById(R.id.text_agregarusuario)
        agregarusuarioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        //obtencion de datos textview
        nombre = root.findViewById(R.id.txtnombre_registro_usuario)
        correo= root.findViewById(R.id.txtcorreo_registro_usuario)
        celular=root.findViewById(R.id.txtnumero_registro_usuario)
        contrasenia1=root.findViewById(R.id.txtContraseniaRegistroU)
        contrasenia2=root.findViewById(R.id.txtOtraContraseniaRegU)
        vendedor=root.findViewById(R.id.rbAdministradorRegU)
        administrador=root.findViewById(R.id.rbAdministradorRegU)



        return root

    }//fin oncreateView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

        radioGroupRol.setOnCheckedChangeListener{
                radioGroupRol,checkedId->
            var ad=rbAdministradorRegU.text.toString()
            var ven=rbVendedorRegiU.text.toString()

            if (checkedId==R.id.rbAdministradorRegU){
                RolEmpleado=ad
                println("selecciono "+ad)
            }

            if (checkedId==R.id.rbVendedorRegiU) {
                RolEmpleado=ven
                println("selecciono " + ven)
            }
        }

        btnagregar.setOnClickListener {
            //obtencion de datos
            var n=nombre.text.toString()
             c=correo.text.toString()
            var ce=celular.text.toString()
            var co1=contrasenia1.text.toString()
            var co2=contrasenia2.text.toString()




            println("DATOS USUARIO PARA REGISTRO "+ n+" "+c+" "+ce+" "+co1+" "+co2+" "+RolEmpleado)


            db.collection("users").document(c).set(
                hashMapOf("Nombre" to nombre.text.toString(),"Celular" to celular.text.toString(),
                    "Correo" to correo.text.toString(), "Contrasenia1" to contrasenia1.text.toString(),"Rol_Empleado" to RolEmpleado ) as Map<String, Any>)


        }//fin boton agregar


    }//fin OnStart




}
