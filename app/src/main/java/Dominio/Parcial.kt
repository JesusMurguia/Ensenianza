package Dominio

import java.util.*

class Parcial(
    var nombre: String,
    var ponderacionAsignacion: Double,
    var ponderacionTrabajo: Double,
    var ponderacionExamen: Double,
    var actividades: ArrayList<Actividad>
)