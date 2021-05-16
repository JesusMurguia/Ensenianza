package Negocio

import Dominio.Alumno
import Dominio.Maestro
import Dominio.Tutor
import Dominio.Usuario
import android.app.Activity
import android.content.Context
import apps.moviles.enseanza.PantallaLogin_2
import apps.moviles.enseanza.PantallaRegistrarteMaestro
import apps.moviles.enseanza.PantallaRegistrate

interface iNegocio {

    fun iniciarSesionUsuario(activity: PantallaLogin_2, usuario:Usuario);
    fun cerrarSesion(): Boolean
    fun subirTarea(): Boolean
    fun registrarTutor(activity: PantallaRegistrate, tutor:Tutor);
    fun registrarMaestro(activity: PantallaRegistrarteMaestro, maestro:Maestro);
    fun registrarAlumno(alumno: Alumno?):String? ;
    fun isMtro(activity: PantallaLogin_2,usuario: Usuario): Boolean
    fun isTutor(activity: PantallaLogin_2,usuario: Usuario): Boolean
}