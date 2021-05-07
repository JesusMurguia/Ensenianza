package Negocio

import Dominio.Alumno
import Dominio.Tutor
import android.content.Context

interface iNegocio {
    fun iniciarSesion(context:Context?,usuario: String?, contrasenia: String?): Boolean?
    fun cerrarSesion(): Boolean
    fun subirTarea(): Boolean
    fun registrarTutor(tutor:Tutor);

    fun registrarAlumno(alumno: Alumno):String? ;
}