import java.text.SimpleDateFormat
import java.util.*


fun main(args:Array<String>) {
    println("GENERO PELICULAS")
    GeneroPeliculas.insertarDatos("COMEDIA", 1)
    GeneroPeliculas.insertarDatos("DRAMA",2)
    GeneroPeliculas.insertarDatos("TERROR" ,3)
    GeneroPeliculas.insertarDatos("CIENCIA FICCION",4)
    GeneroPeliculas.insertarDatos("MUSICAL", 5)
    GeneroPeliculas.mostrar()
    GeneroPeliculas.totalDatos()
    println("BORRAR GENERO CON EL ID 3")
    GeneroPeliculas.delete(3)
    GeneroPeliculas.mostrar()
    GeneroPeliculas.totalDatos()
    println("ACTUALIZAR GENERO CON EL ID 1")
    GeneroPeliculas.udate(1,"ANIME", 2)
    GeneroPeliculas.mostrar()
    GeneroPeliculas.totalDatos()
    println("BUSCAR UN DATO DEL GENERO")
    GeneroPeliculas.buscar("MUSICAL", 5)
    println("GUARDAR DATOS")
    GeneroPeliculas.guardarDatos()

    println("PELICULA")
    Pelicula.insertarDatos(1,"EL PROFESOR CHIFLADO", fecha("09-12-1996"),2)
    Pelicula.insertarDatos(2, "LEYENDAS DE PASION", fecha("19-09-1994"),2)
    Pelicula.insertarDatos(3, "EL EXOSRCISTA", fecha("26-12-1973"),3)
    Pelicula.insertarDatos(4, "MATRIX",fecha("17-06-1999"),2)
    Pelicula.insertarDatos(5, "GREASE", fecha("12-11-1978"),1)
    Pelicula.mostrar()
    Pelicula.totalDatos()
    println("BORRAR PELICULA CON ID 3")
    Pelicula.delete(3)
    Pelicula.mostrar()
    Pelicula.totalDatos()
    println("ACTUALIZAR DATOS PELICULA, ID 2")
    Pelicula.udate(2,3,"BROTHERS", fecha("03-12-2009"),1)
    Pelicula.mostrar()
    Pelicula.totalDatos()
    println("BUSCAR DEL PELICULA")
    Pelicula.buscar(5, "GREASE", fecha("12-11-1978"),1)
    println("GUARDAR DATOS")
    Pelicula.guardarDatos()
    println("\nBORRAR UN GENERO PELICULA ")
    println("TABLAS SIN BORRAR")
    GeneroPeliculas.mostrar()
    Pelicula.mostrar()
    GeneroPeliculas.borrar("2"+"],")
    println("******")


    //--------------------------------------------------------------


    var option = ""
    var codigoP = ""

    while(option != "5") {
        println("\n*******\n" +
                "1 - INGRESAR GENERO\n" +
                "2 - INGRESAR PELICULA\n" +
                "3 - ELIMINAR GENERO\n"+
                "4 - GUARDAR\n"+
                "5 - EXIT")

        option = readLine().toString()
        when (option) {
            "1" -> {
                GeneroPeliculas.mostrar()
                GeneroPeliculas.totalDatos()
                GeneroPeliculas.insert()
                GeneroPeliculas.mostrar()
                GeneroPeliculas.totalDatos()
            }
            "2" -> {
                GeneroPeliculas.mostrar()
                GeneroPeliculas.totalDatos()
                Pelicula.iConsola()
                Pelicula.mostrar()
                GeneroPeliculas.totalDatos()
            }
            "3" -> {
                println("\nTABLAS ACTUAL")
                GeneroPeliculas.mostrar()
                Pelicula.mostrar()
                println("\nIngresar Codigo : ")
                codigoP = readLine().toString()
                GeneroPeliculas.borrar("$codigoP],")
                println("\nTABLAS BORRADO LO REQUERIDO $codigoP")
                GeneroPeliculas.mostrar()
                Pelicula.mostrar()
            }
            "4" -> {
                GeneroPeliculas.guardarDatos()
                Pelicula.guardarDatos()
            }
            else -> {
                println("\n\n")
            }
        }
    }
}

fun fecha(Date:String): Date {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val strDate = "2000-01-01"
    return Date(sdf.parse(strDate).time)
}
