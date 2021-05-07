package Dominio

import java.util.ArrayList

open class Usuario {
    var nombre: String? = null
    var apellido: String? = null
    var email: String? = null
    var conversacionesUsuarios: ArrayList<ConversacionUsuario>?=null;

    constructor(
        nombre: String?, apellido: String?,email:String?,
        conversacionesUsuarios: ArrayList<ConversacionUsuario>?
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.email=email;
        this.conversacionesUsuarios=conversacionesUsuarios;
    }

    constructor(
        nombre: String?, apellido: String?,email:String?

    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.email=email;

    }
    constructor() {}

}