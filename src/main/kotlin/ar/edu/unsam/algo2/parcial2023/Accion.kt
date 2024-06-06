package ar.edu.unsam.algo2.parcial2023
import java.time.DayOfWeek
import kotlin.math.min
import kotlin.random.Random


interface Accion{
    fun ejecutarAccion(programa: Programa,canal: Canal)
}

object DividirPrograma : Accion{
    override fun ejecutarAccion(programa: Programa, canal: Canal) {
        val primerPrograma = Programa(
            titulo = "${programa.tituloEnPalabras()[0]} en el aire!",
            conductores = programa.primerosConductores(),
            presupuesto = programa.presupuesto/2,
            sponsors = programa.sponsors,
            dias = programa.dias,
            duracion = programa.duracion/2
        )
        val segundoPrograma = Programa(
            titulo = programa.tituloEnPalabras()[1] ?: "Programa sin nombre",
            conductores = programa.segundosConductores(),
            presupuesto = programa.presupuesto/2,
            sponsors = programa.sponsors,
            dias = programa.dias,
            duracion = programa.duracion/2
        )
        canal.quitarPrograma(programa)
        canal.nuevoPrograma(primerPrograma)
        canal.nuevoPrograma(segundoPrograma)
    }
}

object Simpsons : Accion{
    override fun ejecutarAccion(programa: Programa, canal: Canal) {
        val simpsons = Programa(
            titulo = "Los Simpsons",
            conductores = mutableListOf(Conductor("Kent Brockman","kentbrockman@simpsons.com")),
            presupuesto = 0.0,
            sponsors = mutableListOf("Duff"),
            dias = programa.dias,
            duracion = programa.duracion
        )
        canal.quitarPrograma(programa)
        canal.nuevoPrograma(simpsons)
    }
}

object fusionarProgramas : Accion{
    override fun ejecutarAccion(programa: Programa, canal: Canal) {

        val programaAFusionar = canal.programaSiguiente(programa)
        val programaFusionado= Programa(
            titulo = elegirTitulo(),
            conductores = mutableListOf(programa.conductorPrincipal(),programaAFusionar.conductorPrincipal()),
            presupuesto = min(programa.presupuesto,programaAFusionar.presupuesto),
            sponsors = elegirPrograma(programa,programaAFusionar).sponsors,
            dias = programa.dias,
            duracion = programa.duracion + programaAFusionar.duracion
        )
        canal.quitarPrograma(programa)
        canal.quitarPrograma(programaAFusionar)
        canal.nuevoPrograma(programaFusionado)
    }
    fun generarRandom() = Random.nextInt(100) > 49
    fun elegirTitulo() = if (generarRandom()) "Impacto total" else "Un buen dia"
    fun elegirPrograma(programa1: Programa,programa2: Programa) = if (generarRandom()) programa1 else programa2
}

class moverDeDia(val nuevosDias : MutableList<DayOfWeek>) : Accion{
    override fun ejecutarAccion(programa: Programa, canal: Canal) {
        programa.dias = nuevosDias
    }
}