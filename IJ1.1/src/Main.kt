import java.util.*

fun main() {
    print("Hola")
    //int edad=31;
    //mutables: que se puede reaccionar
    //no se espesifica tipo de dados
    var edadProfesor = 31
    //Duck Typing:sí algo camina como pato suena como pato vuela como tal.
    var edadCachorrro: Int
    edadCachorrro = 3

    //inmutables: siempre usarlas, no se puede cambiar con el tiempo
    val numeroCuenta = 123456

    //TIpo de variable
    val nombreProfesor = "David Moreno"
    val sueldo = 12.20
    val apellidosProfeso = 'a'
    val fechaNacimiento = Date() //new Date()

    if (sueldo == 12.20) {

    } else {
    }

    when (sueldo) {
        12.20 -> println("Sueldo normal")
        -12.20 -> println("sueldo negativo")
        else -> println("no se reconoce el sueldo")
    }
    val esSueldoMayorAlEstablecido = if (sueldo == 12.20) true else false
    //EXPRESION ? x : y
    calcularSueldo(1000.00,14.00)
    calcularSueldo(tasa=16.00,sueldo=800.00)//parametros nombrados


    val arregloConstante: Array<Int> =arrayOf(1,2,3)
    val arregloCumpleanos: ArrayList<Int> = arrayListOf(30,31,22,23,20)
    print(arregloCumpleanos)
    arregloCumpleanos.add(24)
    arregloCumpleanos.remove(30)
    print(arregloCumpleanos)

    arregloCumpleanos.
        forEach {
            print("\nValor de interacion 1: "+it)
    }
    arregloCumpleanos.forEach { valorInteracion:Int->
        print("\nValor interacion 2: "+ valorInteracion)
    }
    arregloCumpleanos.forEach(
            {valorInteracion:Int->
             print("\nValor interacion 3: " + valorInteracion)
            })

    arregloCumpleanos.
    forEachIndexed { index:Int, it:Int->
        (print("valor de la interacion 4"+it))
    }
    val respuestaArregloForEach=arregloCumpleanos.
    forEachIndexed { index:Int, it:Int->
        print("valor de la interacion 4"+it)
    }
//map hace camio al arreglo directamente.
    val respuestaMap=arregloCumpleanos.map{
        iterador:Int->
        iterador*-1
        }
    val respuestaMapDos=arregloCumpleanos
            .map {iterador:Int->
                val nuevoValor=iterador*-1
                val otroValor=nuevoValor*2
                return@map otroValor
            }

    print("\n"+respuestaMap)
    print("\n"+arregloCumpleanos)
    print("\n"+respuestaMapDos)
    print("\n"+arregloCumpleanos)


    //filter filtra numeros
    val respuestaFilter=arregloCumpleanos
            .filter {iteracion->
                val esMayorA23=iteracion>23
                return@filter esMayorA23

               }
    arregloCumpleanos.filter {
        iteracion:Int->iteracion>23
    }
    print("\n"+respuestaFilter)



}




fun calcularSueldo(
        sueldo:Double,
        tasa:Double=12.20,
        calculoEspecial:Int?=null//opcional
):Double{
    if(calculoEspecial!=null) {
        return sueldo * tasa * calculoEspecial
    }else{
    return sueldo * tasa
    }
}




fun imprimirMensaje():Unit{//Unit es igual que poner void
    println("")
}









