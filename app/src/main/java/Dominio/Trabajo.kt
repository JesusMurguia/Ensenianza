package Dominio

import java.util.*

class Trabajo(
    nombre: String?,
    fechaInicio: Date?,
    fechaEntrega: Date?
) : Actividad(nombre!!, fechaInicio!!, fechaEntrega!!)