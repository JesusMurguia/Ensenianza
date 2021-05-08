package Negocio


import Dominio.Alumno
import Dominio.Tutor
import android.app.Activity
import android.content.Context
import apps.moviles.enseanza.PantallaRegistrate
import java.util.Observable

class FachadaNegocio:iNegocio {
    override fun iniciarSesion(context: Context?, usuario: String?, contrasenia: String?): Boolean? {
        var negocio:CtrlTutor  = CtrlTutor();
        return negocio.iniciarSesion(context,usuario,contrasenia);
    }

    override fun cerrarSesion(): Boolean {
        TODO("Not yet implemented")
    }

    override fun subirTarea(): Boolean {
        TODO("Not yet implemented")
    }

    override fun registrarTutor(activity:PantallaRegistrate,tutor:Tutor,password:String?):Boolean?{
        var negocio:CtrlTutor  = CtrlTutor();
       return negocio.registrarTutor(activity,tutor,password)
    }

    override fun registrarAlumno(alumno: Alumno?):String? {
        var negocioAlumno:CtrlAlumno  = CtrlAlumno();
       return negocioAlumno.registrarAlumno(alumno)

    }

}