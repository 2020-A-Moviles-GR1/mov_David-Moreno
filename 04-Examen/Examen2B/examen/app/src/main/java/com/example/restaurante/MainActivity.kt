package com.example.restaurante

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurante.adapter.PeliculaAdapter
import com.example.restaurante.clases.PlatoHttp
import com.example.restaurante.services.PeliculaService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var listViewPlatos: ListView;
    private var listaPlatos: ArrayList<PlatoHttp> = ArrayList<PlatoHttp>();
    private var TAG = "MAIN_PLATO";
    private var mBound: Boolean = false;
    private lateinit var peliculaService: PeliculaService;

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as PeliculaService.LocalBinder
            peliculaService = binder.getService()
            mBound = true;
            listaPlatos = peliculaService.getAll();
            cargarList(listaPlatos);
        }


        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, PeliculaService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    fun cargarList(listaPlatos: ArrayList<PlatoHttp>) {
        val adapter = PeliculaAdapter(this, listaPlatos);
        try {
            listViewPlatos.adapter = adapter
        } catch (e: Exception) {
            Log.i(TAG, "error: ${e}");
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewPlatos = findViewById(R.id.listPlatoMain) as ListView;

        listViewPlatos.setOnItemClickListener { parent, view, position, id ->
            try{
                val plato = listaPlatos.get(position);
                irActualizarPlato(plato.id);
            } catch (e: java.lang.Exception){ }
        }

        btn_AgregarPlato.setOnClickListener {
            irAgregarPlato()
        }


        btn_eliminarPlato.setOnClickListener {
            irEliminarPlato()
        }

        btn_mapa.setOnClickListener {
            irMapa()
        }

    }

    fun irMapa(){
        val intentExplicito = Intent(
            this,
            MapaPelicula::class.java
        )
        startActivity(intentExplicito)
    }

    fun irEliminarPlato(){
        val intentExplicito = Intent(
            this,
            eliminar_Pelicula::class.java
        )
        startActivity(intentExplicito)
    }

    fun irActualizarPlato(id: Int){
        val intenteExplicito = Intent(
            this,
            actualizarPelicula::class.java
        )
        intenteExplicito.putExtra("id", id);
        startActivity(intenteExplicito)
    }

    fun irVerPlatos(){
        val intentExplicito = Intent(
            this,
            verPelicula::class.java
        )
        startActivity(intentExplicito)
    }


    fun irAgregarPlato(){
        val intentExplicito = Intent(
            this,
            agregar_Pelicula::class.java
        )
        startActivity(intentExplicito)
    }
}