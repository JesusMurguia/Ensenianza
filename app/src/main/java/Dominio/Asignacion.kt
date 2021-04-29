package Dominio

import java.util.*

class Asignacion(
    nombre: String?,
    fechaInicio: Date?,
    fechaEntrega: Date?
) : Actividad(nombre!!, fechaInicio!!, fechaEntrega!!)