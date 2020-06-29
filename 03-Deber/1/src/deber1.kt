import java.io.*
import java.util.*
import javax.naming.Context


//genero y peliculas
fun main() {

    val generoPeliculas: ArrayList<String> = arrayListOf("pAccion","pTerror")
    println(generoPeliculas)

    when (generoPeliculas) {
        "pAccion" -> println("pelicula accion")
        //"pTerror" -> println("pelicula terror")
        else -> println("no se reconoce")
    }
}// fin main










  /*  fun leer(nombre:String, context: Context):String{

        var contenido = ""
        try {
            var file = File("/$nombre.txt")
            var fileExists = file.exists()
            if(fileExists){
                try {
                    val archivo = InputStreamReader(context.OpenFileInput("$nombre.txt"))
                    val br = BufferedReader(archivo)
                    var linea = br.readLine()
                    val todo = StringBuilder()
                    while (linea != null) {
                        todo.append(linea + "\n")
                        linea = br.readLine()
                    }
                    br.close()
                    archivo.close()

                    contenido = todo.toString()

                } catch (e: IOException) {
                }


            } else { }
        }catch (e: IOException){}
        return contenido
    }

*/

var sc: Scanner? = null
var sv: Scanner? = null
var dv: Scanner? = null


fun leerArchivo(genero: String): String? {
    val DatosVehiculo = File("Conductores_vehiculos.txt")

    try {
        sv = Scanner(DatosVehiculo)
        while (sv!!.hasNextLine()) {
            val linea2 = sv!!.nextLine()
            val tk2 = StringTokenizer(linea2, "++")
            /*cedulaConductor = tk2.nextToken()
            nombreConductor = tk2.nextToken()
            apellidoConductor = tk2.nextToken()
            placaVehiculo = tk2.nextToken()
            tipoAuto = tk2.nextToken()
            Marca2 = tk2.nextToken()
            Marca3 = tk2.nextToken()
            totalAsientos = tk2.nextToken()*/
        } //fin while
        sv!!.close()
    } catch (e: FileNotFoundException) {
    }
    return null
}


fun guardarrArchivo() {

    val viajeTxt = File("ViajeTemporal.txt")
    try {
        if (viajeTxt.createNewFile()) {
            println("Se ha creado el archivo")
        }
    } catch (e: IOException) {
        println("No se creó el archivo")
    }
    try {
        val Escribir = BufferedWriter(OutputStreamWriter(FileOutputStream(viajeTxt, true), "utf-8"))
      /*  Escribir.write(aux.getId().toString() + "++")
        Escribir.write(aux.getCedulaConductor().toString() + "++")
        Escribir.write(aux.getNombreConductor().toString() + "++")
        Escribir.write(aux.getApellidoConductor().toString() + "++")
        Escribir.write(aux.getTotalAsientos().toString() + "++")
        Escribir.write(aux.getTipoAuto().toString() + "++")
        Escribir.write(aux.getPlacaVehiculo().toString() + "++")*/
        println("Datos ingresados")
        Escribir.close()
        sv!!.close()
    } catch (e: IOException) {
        println(e.message)
    }
    println("aux")
}
