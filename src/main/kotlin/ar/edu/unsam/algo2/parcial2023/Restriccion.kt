package ar.edu.unsam.algo2.parcial2023

abstract class Restriccion {

    val acciones = mutableListOf<Accion>()

    fun agregarAccion(accion: Accion){
        acciones.add(accion)
    }

    abstract fun seCumple(programa: Programa): Boolean

    fun ejecutarAcciones(programa: Programa,canal: Canal){
        acciones.forEach{it.ejecutarAccion(programa,canal)}
    }
}

class promedioRating(val ratingRequerido : Double) : Restriccion() {
    override fun seCumple(programa: Programa): Boolean = programa.promedioRating() > ratingRequerido
}

class cantidadConductores(val maximoDeConductores : Int) : Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.cantidadDeConductores() <= maximoDeConductores
}

class loConduce(val conductor: Conductor): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.loConduce(conductor)
}

class presupuestoMaximo(val presupuesto: Double): Restriccion(){
    override fun seCumple(programa: Programa): Boolean = programa.presupuesto <= presupuesto
}

class restriccionCompuestaO(val restricciones: List<Restriccion>) : Restriccion(){
    override fun seCumple(programa: Programa): Boolean = restricciones.any{it.seCumple(programa)}
}

class restriccionCompuestaY(val restricciones: List<Restriccion>) : Restriccion(){
    override fun seCumple(programa: Programa): Boolean = restricciones.all{it.seCumple(programa)}
}