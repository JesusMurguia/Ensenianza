package Dominio

import java.util.ArrayList

class Tutor : Usuario {
    var alumno: Alumno? = null


    constructor(nombre: String?, apellido: String?,email: String?, alumno: Alumno,conversacionesUsuarios: ArrayList<ConversacionUsuario>?) : super(
        nombre,
        apellido,
        email,
        conversacionesUsuarios
    ) {
        this.alumno = alumno
    }
    constructor(nombre: String?, apellido: String?,email: String?, password: String?, alumno: Alumno) : super(
        nombre,
        apellido,
        email,
        password
    ) {
        this.alumno = alumno
    }


    constructor(email: String?, password: String?) : super(
        email,
        password
    )
    constructor() {}

}