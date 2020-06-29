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

//ANT->Filtra el arreglo AND OR
    //1)devolver una expresion() true o false
    //2) nuevo arreglo que cumple esas expresion
    //AND ->TRUE, todo lo demas falso
    //OR-> todo es falso, lo demas es verdad
    val respuestaAny:Boolean=arregloCumpleanos.any {
        iterador:Int->return@any iterador<25
    }
    print("\ninteracion any\n "+respuestaAny)

    val respuestaAll=arregloCumpleanos.all {
        iterador:Int->
        return@all iterador>65
    }
    print("\ninteracion all\n "+respuestaAll)

    //Reduce
    //1)Devuelve el acumulado
    //2)en que valor empieza
    //(30, 31, 22, 23 ,20)
    //Devuelven numeros
    val respuestaReduce=arregloCumpleanos//Acumulador en 0
            .reduce{acumulador,iteracion->
                return@reduce acumulador+iteracion
            }
    print("\nrespuestaReduce:\n"+respuestaReduce)

        val respuestaFold=arregloCumpleanos
                .fold(100,{acumulador,iteracion->
                    return@fold acumulador-iteracion
                })

    print("\nrespuestaFold:\n"+respuestaFold)

val respuestaFilter: List<Int> =arregloCumpleanos
        .filter{iteracion:Int->
            val esMayorA23=iteracion>23
            return@filter esMayorA23
        }
    arregloCumpleanos.filter { it>23}
    println(respuestaFilter)
    println(arregloCumpleanos)

//reduce el danio en 20%
    //18
    val vidaActual= arregloCumpleanos
            .map { it*0.8 }
            .filter { it>18 }
            .fold(
                    100.00,
                    {acc,d->acc-d}
            )
    println(vidaActual)





    arregloCumpleanos.forEach(
            {valorInteracion:Int->
             println("\nValor interacion 3: " + valorInteracion)
            })

    arregloCumpleanos.
    forEachIndexed { index:Int, it:Int->
        (println("valor de la interacion 4"+it))
    }
    val respuestaArregloForEach=arregloCumpleanos.
    forEachIndexed { index:Int, it:Int->
        println("valor de la interacion 4"+it)
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
    val respuestaFilter1=arregloCumpleanos
            .filter {iteracion->
                val esMayorA23=iteracion>23
                return@filter esMayorA23

               }
    arregloCumpleanos.filter {
        iteracion:Int->iteracion>23
    }
    println("\n"+respuestaFilter1)


    val nuevoNumeroUno = SumarDosNumerosDos(1, 1)
    val nuevoNumeroDos = SumarDosNumerosDos(null, 1)
    val nuevoNumeroTres = SumarDosNumerosDos(1, null)
    val nuevoNumeroCuatro = SumarDosNumerosDos(null, null)

    println(SumarDosNumerosDos.arregloNumero)
    SumarDosNumerosDos.agregarNumero(1)
    



}//cierre main




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


abstract class NumerosJava{  // val nuevosNumeros = Numeros(1,2)
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(uno:Int, dos:Int){
        numeroUno = uno
        numeroDos = dos
    }
}
abstract class Numeros( // val nuevosNumeros = Numeros(1,2)
        protected var numeroUno: Int,
        protected var numeroDos: Int
) {
}

class Suma(
        uno: Int, // Parametro
        dos: Int // Parametro
) : Numeros(uno, dos) {
    fun sumar(): Int {
        // this.uno o this.dos NO ESTAN DISPONIBLES
        return this.numeroUno + this.numeroDos
    }
}

class SumaDos(
        uno: Int, // Propiedades
        dos: Int // Propiedades
) : Numeros(uno, dos) {

    fun sumar(): Int {
        return this.numeroUno + this.numeroDos
    }
}


class SumarDosNumerosDos(
        uno:Int,
        dos: Int
):Numeros(uno, dos){
    constructor(uno:Int?, dos:Int):this(
            if (uno==null) 0 else uno,
            dos
    ){
        println("Hola 1")
    }
    constructor(uno:Int, dos:Int?) : this (
            uno,
            if (dos == null) 0 else dos
    ){
        println("Hola 2")
    }
    constructor(uno: Int?, dos: Int?):this(
            if(uno==null) 0 else uno,
            if (dos==null) 0 else dos
    ) {

        println("Hola 3")
    }
    companion object{
        val arregloNumero=arrayListOf(1,2,3,4)
        fun agregarNumero(nuevoNumero:Int){
            this.arregloNumero.add(nuevoNumero)
        }
        fun eliminarNumero(posicionNumero:Int){
            this.arregloNumero.removeAt(posicionNumero)
        }
    }



}




