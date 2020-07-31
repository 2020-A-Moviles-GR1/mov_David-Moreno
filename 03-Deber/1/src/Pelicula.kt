
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Pelicula private constructor(var codigoPelicula: Int, var nombrePelicula:String, var fechaDeCreacion: Date, var codigoDePelicula: Int) {
    companion object {
        var count: Int = 0
        private fun create(codigoPelicula: Int, nombrePelicula:String, fechaDeCreacion: Date, codigoDePelicula: Int): Pelicula = Pelicula(codigoPelicula, nombrePelicula, fechaDeCreacion, codigoDePelicula)
        var datosPelicula = arrayListOf<Any>()

        fun insertarDatos(codigoPelicula: Int, nombrePelicula:String, fechaDeCreacion: Date, codigoDePelicula: Int){
            val ingresoDatos = Pelicula.create(codigoPelicula, nombrePelicula, fechaDeCreacion, codigoDePelicula)
            this.datosPelicula.add(arrayListOf(ingresoDatos.codigoPelicula, ingresoDatos.nombrePelicula,ingresoDatos.fechaDeCreacion, ingresoDatos.codigoDePelicula))
        }
        fun totalDatos(){
            println("TOTAL PELICULAS: ${datosPelicula.size}")
        }
        fun mostrar(){
            for ((indice, item) in datosPelicula.withIndex()) {
                println("Indice $indice: $item")
            }
        }
        fun iConsola(){
            println ("Ingrese Codigo Pelicula:")
            val codPeli = readLine()?.toInt() as Int
            println ("Ingrese Nombre Pelicula: ")
            val nombrePeli = readLine()?.toString() as String
            println ("Ingrese Fecha: ")
            val fechaNacimiento = readLine()?.toString() as String
            println ("Ingrese Codigo Pelicula:")
            val codDePel = readLine()?.toInt() as Int
            insertarDatos(codPeli, nombrePeli, fecha(fechaNacimiento), codDePel)
        }
        fun delete(idice: Int){
            this.datosPelicula.removeAt(idice)
        }
        fun udate(indice:Int, codigoPelicula: Int, nombrePelicula:String, fechaDeCreacion: Date, codigoDePelicula: Int){
            this.datosPelicula[indice]=arrayListOf(codigoPelicula, nombrePelicula, fechaDeCreacion, codigoDePelicula)
        }
        fun guardarDatos() {
            var datos: String=""

            for ((indice, item) in datosPelicula.withIndex()) {
                datos += "Indice $indice: $item \n"
            }
            File("BASE PELICULA.txt").writeText(datos+"\n")
            println("Guardado")
        }
        fun buscar(codigoPelicula: Int, nombrePelicula:String, fechaDeCreacion: Date, codigoDePelicula: Int){
            val dato = datosPelicula.find { it == arrayListOf(codigoPelicula, nombrePelicula, fechaDeCreacion, codigoDePelicula) }
            val count: Int = 0
            for ((indice, item) in datosPelicula.withIndex()) {
                if(item ==arrayListOf(codigoPelicula, nombrePelicula, fechaDeCreacion, codigoDePelicula)){
                    println("Indice $indice: $dato")
                    count == indice
                }
            }
            if(dato==null){
                println("Dato no encontrado")
            }
        }
        fun borrarPelicula(codPelic:String){
            var codPeli: String=""
            var count: Int = 0
            var count2: Int = -1
            val eliminarDatosPosicion= arrayListOf<Int>()
            val st = StringTokenizer(datosPelicula.toString())
            while (st.hasMoreTokens()) {
                if((count%11)==0) {
                    if(codPelic==codPeli){
                        eliminarDatosPosicion.add(count2)
                    }
                    count2++
                }
                count++
                codPeli=st.nextToken()
            }
            for (item in eliminarDatosPosicion.asReversed()) {
                delete(item)
            }
        }
        fun fecha(Date:String): Date {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val strDate = "2000-01-01"
            return Date(sdf.parse(strDate).time)
        }
    }
}

