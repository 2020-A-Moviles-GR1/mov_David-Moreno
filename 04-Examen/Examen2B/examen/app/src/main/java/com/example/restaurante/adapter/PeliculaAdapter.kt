package com.example.restaurante.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.restaurante.R
import com.example.restaurante.clases.PlatoHttp
import com.squareup.picasso.Picasso


class PeliculaAdapter(private val mcontext: Context, protected val listaPlatos: List<PlatoHttp>): ArrayAdapter<PlatoHttp>(mcontext, 0, listaPlatos) {
    private lateinit var nombrePlato: TextView;
    private lateinit var precioPlato: TextView;
    private lateinit var imagenPlato: ImageView;

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var layout = LayoutInflater.from(mcontext).inflate(R.layout.item_pelicula, parent, false);
        val plato = listaPlatos[position];
        nombrePlato = layout?.findViewById(R.id.textViewNombreItem) as TextView;
        precioPlato = layout?.findViewById(R.id.textViewPrecioItem) as TextView;
        imagenPlato = layout?.findViewById(R.id.imageViewItem) as ImageView;

        nombrePlato.setText(plato.nombre);
        precioPlato.setText("" + plato.precioUnitario);
        try{
            Picasso.with(mcontext).load(plato.urlImagen).into(imagenPlato);
            //Picasso.get().load(plato.urlImagen).into(imagenPlato);
        } catch (e:Exception){  }
        return layout;
    }

    override fun getItem(position: Int): PlatoHttp? {
        return listaPlatos.get(position)
    }
}