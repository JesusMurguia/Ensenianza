package Negocio


import Dominio.Alumno
import Dominio.Tutor
import android.content.Context

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

    override fun registrarTutor(tutor:Tutor) {
        var negocio:CtrlTutor  = CtrlTutor();
        negocio.registrarTutor(tutor)
    }

    override fun registrarAlumno(alumno: Alumno):String? {
        var negocioAlumno:CtrlAlumno  = CtrlAlumno();
       return negocioAlumno.registrarAlumno(alumno)

    }

}