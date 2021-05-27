package Negocio


import Dominio.*
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

    override fun getUsuario(email:String): Usuario? {
        var negocio: CtrlUsuario = CtrlUsuario();
        negocio.addObserver(this)
        return negocio.getUsuario(email);
    }

    override fun getMtro(email: String): Maestro? {
        TODO("Not yet implemented")
    }

    override fun getTutor(email:String) {
        var negocio: CtrlTutor = CtrlTutor();
        negocio.addObserver(this)
        negocio.getTutor(email);

    }


    override fun getAlumno(activity: AppCompatActivity, key: String) {
        var negocio: CtrlAlumno = CtrlAlumno();
        negocio.addObserver(this)
        negocio.getAlumno(activity,key);
    }

    override fun crearAula(keyMaestro: String, curso: Curso) {
        var negocio: CtrlAula = CtrlAula();
        negocio.addObserver(this)
        negocio.crearAula(keyMaestro,curso);
    }

    override fun getAulas(user_id: String) {
        var negocio: CtrlAula = CtrlAula();
        negocio.addObserver(this)
        negocio.getAulas(user_id);
    }

    override fun getClase(clase_id: String?,nombreMtro:String) {
        var negocio: CtrlClase = CtrlClase();
        negocio.addObserver(this)
        negocio.getClase(clase_id,nombreMtro);
    }

    override fun agregarClase(curso_id:String,clase:Clase) {
        var negocio: CtrlClase = CtrlClase();
        negocio.addObserver(this)
        negocio.agregarClase(curso_id,clase);
    }

    override fun crearAsignacion(materia_id: String, actividad: Actividad) {
        var negocio: CtrlAsignacion = CtrlAsignacion();
        negocio.crearAsignacion(materia_id,actividad);
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