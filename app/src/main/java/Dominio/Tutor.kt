package Dominio

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


class Tutor : Usuario ,Serializable{
    var alumno: Alumno? = null



   // constructor(nombre: String?, apellido: String?,email: String?, alumno: Alumno,conversacionesUsuarios: ArrayList<ConversacionUsuario>?) : super(
   //     nombre,
  //      apellido,
   //     email,
   //     conversacionesUsuarios
  //  ) {
   //     this.alumno = alumno
  //  }
    constructor(nombre: String?, apellido: String?,email: String?, password: String?, alumno: Alumno) : super(
        nombre,
        apellido,
        email,
        password
    ) {
        this.alumno = alumno
    }

    constructor(nombre: String?, apellido: String?,email: String?) : super(
        nombre,
        apellido,
        email,

    )
    constructor(email: String?, password: String?) : super(
        email,
        password
    )
    constructor() {}





}