package Negocio


import Dominio.Alumno
import Dominio.Maestro
import Dominio.Tutor
import Dominio.Usuario
import androidx.appcompat.app.AppCompatActivity
import apps.moviles.ensenianza.PantallaLogin_2
import apps.moviles.ensenianza.PantallaRegistrarteMaestro
import apps.moviles.ensenianza.PantallaRegistrate
import java.util.*

class FachadaNegocio : iNegocio, java.util.Observer, Observable() {


    override fun iniciarSesionUsuario(activity: PantallaLogin_2, usuario: Usuario) {
        var negocio: CtrlUsuario = CtrlUsuario();
        negocio.addObserver(this);
        negocio.iniciarSesionUsuario(activity, usuario);
    }


    override fun isMtroOrTutor(activity: AppCompatActivity, usuario: Usuario, tipo: String): Boolean {
        var negocio:CtrlUsuario  = CtrlUsuario();
        negocio.addObserver(this);
        return negocio.isMtroOrTutor(activity,usuario,tipo)
    }

    override fun getEmail(): String {
        var negocio:CtrlUsuario  = CtrlUsuario();
        return negocio.getEmail();
    }

    override fun getMtro(email:String): Maestro? {
        var negocio: CtrlMaestro = CtrlMaestro();
        negocio.addObserver(this)
        return negocio.getMtro(email);
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
        negocio.registrarMtro(activity, maestro);
    }

    override fun registrarAlumno(alumno: Alumno?): String? {
        var negocioAlumno: CtrlAlumno = CtrlAlumno();
        return negocioAlumno.registrarAlumno(alumno)

    }

    override fun update(p0: Observable?, p1: Any?) {


            setChanged();
            notifyObservers(p1);
            clearChanged();





    }

}