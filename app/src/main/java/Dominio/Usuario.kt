package Dominio

import java.util.ArrayList

open class Usuario {
    var nombre: String? = null
    var apellido: String? = null
    var conversacionesUsuarios: ArrayList<ConversacionUsuario>?=null;

    constructor(
        nombre: String?, apellido: String?,
        conversacionesUsuarios: ArrayList<ConversacionUsuario>?
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.conversacionesUsuarios=conversacionesUsuarios;
    }

    constructor() {}

}