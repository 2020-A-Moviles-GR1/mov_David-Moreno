package com.example.restaurante

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurante.adapter.PeliculaAdapter
import com.example.restaurante.clases.CrearPlatoHttp
import com.example.restaurante.clases.PlatoHttp
import com.example.restaurante.services.PeliculaService


class actualizarPelicula : AppCompatActivity() {
    private lateinit var peliculaService: PeliculaService;
    private lateinit var nombrePlatoTextView: EditText;
    private lateinit var precioPlatoTextView: EditText;
    private lateinit var latitudEditText: EditText;
    private lateinit var longitudEditText: EditText;
    private lateinit var urlReferenciaEditText: EditText;
    private lateinit var urlImagenEditText: EditText;
    private lateinit var btnActualizarPlato: Button;
    private lateinit var listViewPlatos: ListView;
    private var platoObtenido: PlatoHttp? = null;
    private var listaPlatos: ArrayList<PlatoHttp> = ArrayList<PlatoHttp>();
    private var padre = this;
    var idActualizar: Int = 0;
    private var TAG = "ACTUALIZAR_PLATO";
    private var mBound: Boolean = false;

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as PeliculaService.LocalBinder
            peliculaService = binder.getService()
            mBound = true;
            listaPlatos = peliculaService.getAll();
            cargarList(listaPlatos);
            platoObtenido = peliculaService.getId(idActualizar);
            if (platoObtenido != null) {
                nombrePlatoTextView.setText(platoObtenido?.nombre);
                precioPlatoTextView.setText(platoObtenido?.precioUnitario.toString());
                latitudEditText.setText(platoObtenido?.latitud.toString());
                longitudEditText.setText(platoObtenido?.longitud.toString());
                urlReferenciaEditText.setText(platoObtenido?.url);
                urlImagenEditText.setText(platoObtenido?.urlImagen);
            } else {
                Toast.makeText(padre, "Pelicula no cargado!", Toast.LENGTH_SHORT).show();
            }
        }


        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    fun cargarList(listaPlatos: ArrayList<PlatoHttp>) {
        val adapter = PeliculaAdapter(this, listaPlatos);
        try {
            padre.listViewPlatos.adapter = adapter
        } catch (e: Exception) {
            Log.i(TAG, "error: ${e}");
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_pelicula)
        var value: Int = intent.getIntExtra("id", 0);
        this.idActualizar = value;
        nombrePlatoTextView = findViewById(R.id.plato_ha_actualizar) as EditText;
        precioPlatoTextView = findViewById(R.id.precio_plato) as EditText;
        latitudEditText = findViewById(R.id.editTextLatitudActualizar) as EditText;
        longitudEditText = findViewById(R.id.editTextLongitudActualizar) as EditText;
        urlReferenciaEditText = findViewById(R.id.editTextTextMultiLineurlReferencia) as EditText;
        urlImagenEditText = findViewById(R.id.editTextTextMultiLineUrlImagen) as EditText;
        btnActualizarPlato = findViewById(R.id.btn_ActualizarNuevoPlato) as Button;
        //listViewPlatos = findViewById(R.id.list_menuPlato) as ListView;
        btnActualizarPlato.setOnClickListener {
            onClickButtonActualizar();
        }
    }


    fun onClickButtonActualizar() {
        val nombre: String = nombrePlatoTextView.text.toString()
        val valorPlato: Float = (precioPlatoTextView.text.toString()).toFloat();
        val latitud:Float = (latitudEditText.text.toString()).toFloat()
        val longitud:Float = (longitudEditText.text.toString()).toFloat()
        val urlReferencia:String = urlReferenciaEditText.text.toString()
        val urlImagen:String = urlImagenEditText.text.toString()
        var newPlato = CrearPlatoHttp(nombre = nombre, precioUnitario = valorPlato,
            url = urlReferencia, urlImagen = urlImagen, latitud = latitud, longitud = longitud);
        var platoActualizado: PlatoHttp? = peliculaService.update(idActualizar, newPlato);
        if (platoActualizado != null && platoObtenido != null) {
            if (!listaPlatos.isEmpty()) {
                var index: Int = -1;
                listaPlatos.forEachIndexed { ind, platoHttp ->
                    if (platoHttp.id == platoObtenido?.id) {
                        index = ind;
                        return@forEachIndexed
                    }
                };
                if (index != -1) {
                    listaPlatos.set(index, platoActualizado);
                    cargarList(listaPlatos);
                };
            }
            platoObtenido = platoActualizado;
            Toast.makeText(this, "Pelicula actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pelicula no actualizado", Toast.LENGTH_SHORT).show();
        }
    }

    inline fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}