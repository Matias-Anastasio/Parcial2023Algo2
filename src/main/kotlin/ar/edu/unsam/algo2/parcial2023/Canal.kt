package ar.edu.unsam.algo2.parcial2023

class Canal {
    val programas = mutableListOf<Programa>()
    val programasEnRevision = mutableListOf<Programa>()
    val observers = mutableListOf<CanalObserver>()

    fun agregarRestriccionAlPrograma(programa: Programa,restriccion: Restriccion){
        programa.agregarRestriccion(restriccion)
    }

    fun agregarAccionALaRestriccion(restriccion: Restriccion,accion: Accion){
        restriccion.agregarAccion(accion)
    }

    fun agregarProgramaEnRevision(programa: Programa){
        programasEnRevision.add(programa)
    }

    fun realizarRevision(){
        programasEnRevision.forEach{it.revisar(this)}
    }

    fun nuevoPrograma(programa: Programa){
        programas.add(programa)
    }

    fun quitarPrograma(programa: Programa){
        programas.remove(programa)
    }

    fun programaSiguiente(programa: Programa): Programa {
        val indiceSiguiente : Int= programas.indexOf(programa) + 1
        return if(programas.size > indiceSiguiente)
            programas[indiceSiguiente]
        else
            programas[0]
    }

    fun quitarDeRevision(programa: Programa){
        programasEnRevision.remove(programa)
    }

    fun sincronizarProgramasEnRevision(){
        programasEnRevision.forEach{it->
            if (!programas.contains(it)) quitarDeRevision(it)
        }
    }
}