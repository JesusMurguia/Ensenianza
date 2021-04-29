package Dominio

import java.util.ArrayList

class Tutor : Usuario {
    var alumno: Alumno? = null

    constructor(nombre: String?, apellido: String?, alumno: Alumno?,conversacionesUsuarios: ArrayList<ConversacionUsuario>?) : super(
        nombre,
        apellido,
        conversacionesUsuarios
    ) {
        this.alumno = alumno
    }

    constructor() {}

}