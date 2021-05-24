package Dominio

import java.io.Serializable

class Maestro: Usuario,Serializable{

    constructor(nombre: String?, apellido: String?,email: String?, password: String?) : super(
        nombre,
        apellido,
        email,
        password
    )

    constructor(nombre: String?, apellido: String?,email: String?) : super(
        nombre,
        apellido,
        email
    )
    constructor(email: String?, password: String?) : super(
        email,
        password
    )
}