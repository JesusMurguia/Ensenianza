package Dominio

import java.io.Serializable
import java.util.ArrayList

@JsonIgnoreProperties(ignoreUnknown=true)
open class Usuario :Serializable{
    var nombre: String? = null
    var lastname: String? = null
    var email: String? = null
    var password: String? = null
    var conversacionesUsuarios: ArrayList<ConversacionUsuario>?=null;
    var key: String? = null



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

    @JvmName("getNombre1")
    fun getNombre():String?{
        return this.nombre;
    }
}

annotation class JsonIgnoreProperties(val ignoreUnknown: Boolean)
