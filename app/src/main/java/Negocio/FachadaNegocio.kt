package Negocio


import Dominio.Alumno
import Dominio.Maestro
import Dominio.Tutor
import Dominio.Usuario
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import apps.moviles.enseanza.PantallaLogin_2
import apps.moviles.enseanza.PantallaRecordarUsuario
import apps.moviles.enseanza.PantallaRegistrarteMaestro
import apps.moviles.enseanza.PantallaRegistrate
import java.util.*

class FachadaNegocio : iNegocio, java.util.Observer, Observable() {


    override fun iniciarSesionUsuario(activity: PantallaLogin_2, usuario: Usuario) {
        var negocio: CtrlUsuario = CtrlUsuario();
        negocio.addObserver(this);
        negocio.iniciarSesionUsuario(activity, usuario);
    }

    override fun cerrarSesion() {
        var negocio: CtrlUsuario = CtrlUsuario();
        negocio.cerrarSesion()
    }

    override fun isSignedIn(): Boolean {
        var negocio: CtrlUsuario = CtrlUsuario();
        return negocio.isSignedIn();

    }

    override fun subirTarea(): Boolean {
        TODO("Not yet implemented")
    }

    override fun registrarTutor(activity: PantallaRegistrate, tutor: Tutor) {
        var negocio: CtrlTutor = CtrlTutor();
        negocio.addObserver(this);
        negocio.registrarTutor(activity, tutor);
    }

    override fun registrarMaestro(activity: PantallaRegistrarteMaestro, maestro: Maestro) {
        var negocio: CtrlMaestro = CtrlMaestro();
        negocio.addObserver(this);
        negocio.registrarTutor(activity, maestro);
    }

    override fun registrarAlumno(alumno: Alumno?): String? {
        var negocioAlumno: CtrlAlumno = CtrlAlumno();
        return negocioAlumno.registrarAlumno(alumno)

    }

    override fun update(p0: Observable?, p1: Any?) {

        //esta lo sua al registrase e ingresar sesion de mtro y tutor
        var isSuccessful: Boolean? = p1.toString().toBoolean();

        //patron observer
        setChanged();
        notifyObservers(isSuccessful);
        clearChanged();


    }

}