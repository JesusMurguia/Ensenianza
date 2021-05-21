package Dominio

import java.util.ArrayList

@JsonIgnoreProperties(ignoreUnknown=true)
open class Usuario {
    var nombre: String? = null
    var lastname: String? = null
    var email: String? = null
    var password: String? = null
    var conversacionesUsuarios: ArrayList<ConversacionUsuario>?=null;

    constructor(
        nombre: String?, apellido: String?,email:String?,
        conversacionesUsuarios: ArrayList<ConversacionUsuario>?
    ) {
        this.nombre = nombre
        this.lastname = apellido
        this.email=email;
        this.conversacionesUsuarios=conversacionesUsuarios;
    }

    constructor(
        nombre: String?, apellido: String?,email:String?,password: String?

    ) {
        this.nombre = nombre
        this.lastname = apellido
        this.email=email;
        this.password=password;

    }
    constructor(
        nombre: String?, apellido: String?,email:String?

    ) {
        this.nombre = nombre
        this.lastname = apellido
        this.email=email;


    }
    constructor(
        email:String?,password:String?

    ) {
        this.email=email;
        this.password = password


    }
    constructor() {}

}

annotation class JsonIgnoreProperties(val ignoreUnknown: Boolean)
