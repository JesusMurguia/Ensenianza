package Negocio

import Dominio.Alumno
import Dominio.Maestro
import Dominio.Tutor
import android.app.Activity
import android.content.Context
import apps.moviles.enseanza.PantallaLogin_2
import apps.moviles.enseanza.PantallaRegistrarteMaestro
import apps.moviles.enseanza.PantallaRegistrate

interface iNegocio {
    fun iniciarSesion(activity: PantallaLogin_2, tutor:Tutor);
    fun cerrarSesion(): Boolean
    fun subirTarea(): Boolean
    fun registrarTutor(activity: PantallaRegistrate, tutor:Tutor);
    fun registrarMaestro(activity: PantallaRegistrarteMaestro, maestro:Maestro);
    fun registrarAlumno(alumno: Alumno?):String? ;
}