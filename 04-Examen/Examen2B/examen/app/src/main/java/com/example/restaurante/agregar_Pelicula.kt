package com.example.restaurante

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.restaurante.clases.CrearPlatoHttp
import com.example.restaurante.clases.PlatoHttp
import com.example.restaurante.services.PeliculaService
import kotlinx.android.synthetic.main.activity_agregar__pelicula.*
import java.lang.Exception


class agregar_Pelicula : AppCompatActivity() {
    private lateinit var peliculaService: PeliculaService;
    private lateinit var nombrePlatoEditText: EditText;
    private lateinit var precioPlatoEditText: EditText;
    private lateinit var latitudEditText: EditText;
    private lateinit var longitudEditText: EditText;
    private lateinit var urlReferenciaEditText: EditText;
    private lateinit var urlImagenEditText: EditText;

    private var TAG = "AGREGAR_PLATO";
     private var mBound: Boolean = false;

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as PeliculaService.LocalBinder
            peliculaService = binder.getService()
            mBound = true
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar__pelicula)
        nombrePlatoEditText = findViewById(R.id.nuevo_plato) as EditText;
        precioPlatoEditText = findViewById(R.id.valor_plato) as EditText;
        latitudEditText = findViewById(R.id.editTextlatitudAgreagar) as EditText;
        longitudEditText = findViewById(R.id.editTextLongitudAgregar) as EditText;
        urlReferenciaEditText = findViewById(R.id.editTextUrlReferenciaAgregar) as EditText;
        urlImagenEditText = findViewById(R.id.editTextUrlImagenAgregar) as EditText;
        btn_GuardarPlato.setOnClickListener {
            guardarPlato()
        }
    }


    fun guardarPlato() {
        val nombre:String = nombrePlatoEditText.text.toString()
        val valorPlato:Float = (precioPlatoEditText.text.toString()).toFloat()
        val latitud:Float = (latitudEditText.text.toString()).toFloat()
        val longitud:Float = (longitudEditText.text.toString()).toFloat()
        val urlReferencia:String = urlReferenciaEditText.text.toString()
        val urlImagen:String = urlImagenEditText.text.toString()
        var newPlato = CrearPlatoHttp(nombre = nombre, precioUnitario = valorPlato,
        url = urlReferencia, urlImagen = urlImagen, latitud = latitud, longitud = longitud);
        var padre = this;
        try{
            var platoCreated: PlatoHttp? = peliculaService.create(newPlato);
            if (platoCreated != null) {
                Toast.makeText(this, "Pelicula creado correctamente!", Toast.LENGTH_SHORT).show();
                nombrePlatoEditText.setText("");
                precioPlatoEditText.setText("");
                latitudEditText.setText("");
                longitudEditText.setText("");
                urlReferenciaEditText.setText("");
                urlImagenEditText.setText("");
            } else {
                Toast.makeText(this, "Pelicula no creado!", Toast.LENGTH_SHORT). show();
            }
        }catch (e: Exception){
            Log.i(TAG, "error: ${e.toString()}");
        }

    }

}
