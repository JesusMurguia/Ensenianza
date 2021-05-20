package Dominio

import java.io.Serializable
import java.util.ArrayList

open class Usuario {
    var nombre: String? = null
    var apellido: String? = null
    var email: String? = null
    var password: String? = null
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
        nombre: String?, apellido: String?,email:String?,password: String?

    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.email=email;
        this.password=password;

    }

    constructor(
        email:String?,password:String?

    ) {
        this.email=email;
        this.password = password


    }
    constructor() {}

}