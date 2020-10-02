package com.example.johnny2.ui.caja

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.johnny2.R
import com.example.johnny2.ui.base.BDD
import kotlinx.android.synthetic.main.fragment_inicio.*

class CajaFragment : Fragment() {

    private lateinit var cajaViewModel: CajaViewModel
    private var listaTotalvendidas=arrayListOf<Double>()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        cajaViewModel = ViewModelProviders.of(this).get(CajaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_inicio, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        cajaViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onStart() {
        super.onStart()
        btnactualizar.setOnClickListener {
            listaTotalvendidas= BDD.listaventas
            var suma:Double=0.0
            listaTotalvendidas.forEach {
                suma+=it
                println("recuperado: " + it)
            }
            val totall=txtInicioCaja.getText().toString().toDouble()+suma
            txtventas.setText(suma.toString())
            txtTotalVentas.setText(totall.toString())


        }

    }

}//fin clase
