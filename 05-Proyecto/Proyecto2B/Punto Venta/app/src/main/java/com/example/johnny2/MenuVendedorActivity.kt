package com.example.johnny2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MenuVendedorActivity : AppCompatActivity() {
//usuario:monita@gmail.com, pass:12345moni
    //val user = "names"
    private lateinit var User: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_vendedor)

        User = findViewById(R.id.txtUser2)

        val objetoIntent: Intent =intent
        val user=objetoIntent.getStringExtra("Nombre")
        User.setText("¡Bienvenido VENDEDOR: " +"\n"+ user + "!")

    }
}
