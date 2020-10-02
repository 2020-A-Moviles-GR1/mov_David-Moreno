package com.example.johnny2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_menu.*

class MenuAdministradorActivity : AppCompatActivity() {
    //val user = "names"
    private lateinit var User: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
       User = findViewById(R.id.txtUser)

        val objetoIntent: Intent =intent
        val user=objetoIntent.getStringExtra("Nombre")
        User.setText("¡Bienvenido ADMINISTRADOR: "+"\n" + user + "!")

    }
}
