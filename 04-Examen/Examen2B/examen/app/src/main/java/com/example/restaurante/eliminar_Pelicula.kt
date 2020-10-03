package com.example.restaurante

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.restaurante.adapter.PeliculaAdapter
import com.example.restaurante.clases.PlatoHttp
import com.example.restaurante.services.PeliculaService

class eliminar_Pelicula : AppCompatActivity() {
    private lateinit var listViewPlatos: ListView;
    private var listaPlatos: ArrayList<PlatoHttp> = ArrayList<PlatoHttp>();
    private var TAG = "ELIMINAR_PLATO";
    private var mBound: Boolean = false;
    private lateinit var peliculaService: PeliculaService;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_pelicula);

        listViewPlatos = findViewById(R.id.listViewEliminar) as ListView;

        listViewPlatos.setOnItemClickListener { parent, view, position, id ->
            try{
                val plato = listaPlatos.get(position);
                eliminarPlato(plato.id);
            } catch (e: java.lang.Exception){ }
        }
    }

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

    fun eliminarPlato (id: Int){
        var platoActualizado: PlatoHttp? = peliculaService.delete(id);
            if (platoActualizado != null) {
            if (!listaPlatos.isEmpty()) {
                var index: Int = -1;
                listaPlatos.forEachIndexed { ind, platoHttp ->
                    if (platoHttp.id == platoActualizado?.id) {
                        index = ind;
                        return@forEachIndexed
                    }
                };
                if (index != -1) {
                    listaPlatos.removeAt(index);
                    cargarList(listaPlatos);
                };

            }
            Toast.makeText(this, "Pelicula eliminado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pelicula no eliminado", Toast.LENGTH_SHORT).show();
        }
    }


}