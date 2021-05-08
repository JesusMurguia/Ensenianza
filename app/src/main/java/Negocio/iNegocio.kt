package Negocio

import Dominio.Alumno
import Dominio.Tutor
import android.app.Activity
import android.content.Context
import apps.moviles.enseanza.PantallaRegistrate

interface iNegocio {
    fun iniciarSesion(context:Context?,usuario: String?, contrasenia: String?): Boolean?
    fun cerrarSesion(): Boolean
    fun subirTarea(): Boolean
    fun registrarTutor(activity: PantallaRegistrate, tutor:Tutor, password:String?):Boolean?;

    fun registrarAlumno(alumno: Alumno?):String? ;
}