package com.example.restaurante

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.restaurante.clases.PicassoMarker
import com.example.restaurante.clases.PlatoHttp
import com.example.restaurante.services.PeliculaService

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import java.lang.Exception

class MapaPelicula : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false;
    private lateinit var listViewPlatos: ListView;
    private var listaPlatos: ArrayList<PlatoHttp> = ArrayList<PlatoHttp>();
    private var TAG = "MAPA_PLATO";
    private var mBound: Boolean = false;
    private lateinit var peliculaService: PeliculaService;

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as PeliculaService.LocalBinder
            peliculaService = binder.getService()
            mBound = true;
            listaPlatos = peliculaService.getAll();
            Log.i(TAG, "asd")
            listaPlatos.forEach {
                addPointmap(it.latitud, it.longitud, it.nombre, it.urlImagen);
            }

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
        setContentView(R.layout.activity_mapa_pelicula)
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        establecerConfiguracionMapa(mMap)
        val cci = LatLng(-0.178067, -78.485594)
        val puntoUsuario = LatLng(-0.179204, -78.484189)
        val titulo = "CCI"
        val zoom = 17f
        anadirMarcador(cci, titulo)
        moverCamaraConZoom(puntoUsuario, zoom)
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }
    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            // this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }

    }

    fun addPointmap(latitud: String, longitud:String, titulo: String, urlImagen: String){
        try{
            Log.i(TAG, "asdasdasdad");Log.i(TAG, "titulo ${titulo}");
            var lat:Double = latitud.toDouble();
            var log:Double = longitud.toDouble();
            var latLon = LatLng(lat, log);
            Log.i(TAG, "a8520156565");
            var mMaler = mMap.addMarker(MarkerOptions().position(latLon).title(titulo));

            val marker = PicassoMarker(mMaler);
            Picasso.with(this).load(urlImagen).resize(150, 150).into(marker);

            mMap.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { melbourne ->
                val uri: Uri =
                    Uri.parse("${urlImagen}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
            })
        } catch (e: Exception){
            Log.i(TAG, "error: ${e.toString()}")
        }

    }

    fun solicitarPermisos() {
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) {
            Log.i("mapa", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, // Contexto
                arrayOf( // Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //  Codigo que esperamos
            )
        }
    }

}