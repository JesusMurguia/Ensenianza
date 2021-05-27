package Dominio

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Maestro: Usuario, Parcelable {

    lateinit var cursos: ArrayList<Curso>;

    constructor(nombre: String?, apellido: String?, email: String?, password: String?) : super(
        nombre,
        apellido,
        email,
        password
    )

    constructor(nombre: String?, apellido: String?, email: String?) : super(nombre, apellido, email)
    constructor(email: String?, password: String?) : super(email, password)
    constructor() : super()
    constructor(source: Parcel) : super(source)


}