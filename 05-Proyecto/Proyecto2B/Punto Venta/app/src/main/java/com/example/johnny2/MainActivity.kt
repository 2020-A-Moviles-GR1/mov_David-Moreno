package com.example.johnny2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //defining view objects
   private lateinit var Usuario: TextView
    private lateinit var Contrasenia: TextView
    private lateinit var IngresarButton: Button
   // private val progressDialog= ProgressDialog(this)
    //Declaramos un objeto firebaseAuth

   private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Contrasenia=findViewById(R.id.txtContraseña)
        Usuario=findViewById(R.id.txtUsuario)
        IngresarButton=findViewById(R.id.btIngresar)

        firebaseAuth=FirebaseAuth.getInstance()

    }


    private fun loginUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        val usuario = Usuario.text.toString()
        val contraseña = Contrasenia.text.toString()
        //enviar nombre usuario a otro activiti



        //Verificamos que las cajas de texto no esten vacías
        if (!TextUtils.isEmpty(usuario)&& !TextUtils.isEmpty(contraseña))
        {
            firebaseAuth.signInWithEmailAndPassword(usuario,contraseña)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){

                        if(TextUtils.equals(usuario,"vido21moreno@gmail.com")){
                            action1(usuario)
                        }else{
                            action2(usuario)
                        }


                    } else {
                        Toast.makeText(this, "Error en la autenticacion", Toast.LENGTH_LONG).show()
                    }
                }


        }else {
            Toast.makeText(this, "Debe ingresar correctamente los datos", Toast.LENGTH_LONG).show()
        }


    }//fin fun login


   private fun action1(usa:String){
       /*val intent:Intent=Intent(this, MenuAdministradorActivity::class.java)*/
       val intent =Intent(this, Barra_Menu_Activity::class.java)
       intent.putExtra("Nombre",usa)
       startActivity(intent)


    }
    private fun action2(usa:String){
        val intent:Intent=Intent(this, MenuVendedorActivity::class.java)
        intent.putExtra("Nombre",usa)
        startActivity(intent)
    }

    fun login(view: View) {
        loginUsuario()
    }


}//fin MainActivity
