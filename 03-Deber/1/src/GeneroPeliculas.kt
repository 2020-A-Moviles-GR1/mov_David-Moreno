import java.io.File
import java.util.*

class GeneroPeliculas private constructor(var genero:String, var idGenero: Int) {
    companion object {
        var count: Int = 0
        private fun create(genero:String, idGenero: Int): GeneroPeliculas = GeneroPeliculas(genero, idGenero)
        var datosPeliculas = arrayListOf<Any>()

        fun insertarDatos(genero:String, idGenero: Int){
            val ingresoDatos = GeneroPeliculas.create(genero, idGenero)
            this.datosPeliculas.add(arrayListOf( ingresoDatos.genero,ingresoDatos.idGenero))
        }

        fun totalDatos(){
            println("TOTAL GENERO: ${datosPeliculas.size}")
        }
        fun mostrar(){
            for ((indice, item) in datosPeliculas.withIndex()) {
                println("Indice $indice: $item")
            }
        }
        fun insert(){
            println ("Ingrese Codigo: ")
            val codDepartamento = readLine()?.toInt() as Int
            println ("Ingrese GENERO: ")
            val nombreDepartamento = readLine()?.toString() as String
            insertarDatos(nombreDepartamento,codDepartamento)
        }
        
        fun delete(Indice: Int){
            this.datosPeliculas.removeAt(Indice)
        }
        fun udate(indice: Int, genero:String,idGenero: Int){
            this.datosPeliculas[indice]=arrayListOf(genero, idGenero)
        }
        fun guardarDatos() {
            var datos: String=""
            for ((indice, item) in datosPeliculas.withIndex()) {
                datos += "Indice $indice: $item \n"
            }
            File("BASE GENERO.txt").writeText(datos+"\n")
            println("Datos Guardados")
        }
        fun buscar( genero:String,idGenero: Int) {
            val dato = datosPeliculas.find { it == arrayListOf(genero,idGenero) }
            val count: Int = 0
            for ((indice, item) in datosPeliculas.withIndex()) {
                if(item ==arrayListOf(genero,idGenero)){
                    println("Indice $indice: $dato")
                    count == indice
                }
            }
            if(dato==null){
                println("Dato no encontrado")
            }
        }
        fun borrar(codPelis:String){
            var codPel: String=""
            var count: Int = 0
            var count2: Int = -1
            val st = StringTokenizer(datosPeliculas.toString())
            while (st.hasMoreTokens()) {
                if((count%3)==0) {
                    if(codPelis==codPel){
                        Pelicula.borrarPelicula(codPel)
                        delete(count2)
                    }
                    count2++
                }
                count++
                codPel=st.nextToken()
            }
        }
    }
}
