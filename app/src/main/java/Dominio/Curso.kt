package Dominio

import java.util.*

class Curso{
    lateinit var nombre: String
    lateinit var parciales: ArrayList<Parcial>
    lateinit var cursosAlumnos: ArrayList<CursoAlumno>

    constructor(nombre: String) {
        this.nombre = nombre
    }

    constructor()


}