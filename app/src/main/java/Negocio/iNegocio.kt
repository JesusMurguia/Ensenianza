package Negocio

import Dominio.*
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import apps.moviles.ensenianza.PantallaLogin_2
import apps.moviles.ensenianza.PantallaRegistrarteMaestro
import apps.moviles.ensenianza.PantallaRegistrate

interface iNegocio {

  fun iniciarSesionUsuario(activity: PantallaLogin_2, usuario: Usuario);

  //login
  fun cerrarSesion()
  fun isSignedIn(): Boolean
  fun subirTarea(): Boolean

  fun registrarTutor(activity: PantallaRegistrate, tutor: Tutor);
  fun registrarMaestro(activity: PantallaRegistrarteMaestro, maestro: Maestro);
  fun registrarAlumno(alumno: Alumno?): String?;
  fun isMtroOrTutor(activity: AppCompatActivity, usuario: Usuario, tipo: String): Boolean
  fun getEmail():String
  fun getUsuario(email:String):Usuario?
  fun getMtro(email:String):Maestro?
  fun getTutor(email:String)
  fun getAlumno(activity: AppCompatActivity,key:String)
  fun crearAula(keyMaestro: String, curso: Curso);
}