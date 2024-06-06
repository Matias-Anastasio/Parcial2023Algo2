package ar.edu.unsam.algo2.parcial2023

import java.time.DayOfWeek

data class Conductor(
    val nombre: String,
    val mail: String
)

class Programa(
    val titulo: String,
    val conductores: MutableList<Conductor>,
    var presupuesto: Double,
    val sponsors: MutableList<String>,
    var dias : MutableList<DayOfWeek>,
    var duracion: Int,
) {
    val ratings = mutableListOf<Double>()
    val restricciones = mutableListOf<Restriccion>()

    fun agregarRestriccion(restriccion: Restriccion){
        restricciones.add(restriccion)
    }

    fun promedioRating() = ratings.takeLast(5).average()

    fun cantidadDeConductores() : Int = conductores.size

    fun loConduce(conductor: Conductor) = conductores.contains(conductor)

    fun revisar(canal: Canal){
        val restriccionIncumplida = restricciones.find { !it.seCumple(this) }
        restriccionIncumplida?.ejecutarAcciones(this,canal)
    }

    fun tituloEnPalabras() = titulo.split(" ")
    fun conductorPrincipal() : Conductor= conductores[0]
    fun primerosConductores() = conductores.take(conductores.size/2).toMutableList()
    fun segundosConductores() = conductores.filterNot { it in primerosConductores() }.toMutableList()
    fun mailsDeConductores() = conductores.map { it.mail }
}