package Dominio

import java.util.*

class Asignacion{


     lateinit var nombre: String
     lateinit var  fechaInicio: String
     lateinit var fechaEntrega: String
    lateinit var asignacion_id: String


    constructor(asignacion_id: String) {
        this.asignacion_id = asignacion_id
    }
}



