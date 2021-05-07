package Negocio

import Dominio.Tutor
import android.content.Context
import com.google.firebase.database.FirebaseDatabase


class CtrlTutor {

  //  private var accesoDatos:FachadaDatos;
    constructor(){
     //   accesoDatos=FabricaDatos.crearFachadaDatos();
    }

    fun iniciarSesion(context: Context?, usuario: String?, password: String?): Boolean? {

       // var token= accesoDatos.iniciarSesion(context,usuario,password);
        return true;

    }

    fun cerrarSesion(): Boolean {
        return true
    }

    fun subirTarea(): Boolean {
        return true
    }

    fun registrarTutor(tutor: Tutor) {

        //guardar usuario
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users").push();

        myRef.child("nombre").setValue(tutor.nombre);
        myRef.child("lastname").setValue(tutor.apellido);
        myRef.child("email").setValue(tutor.email);

        var user_id=myRef.key.toString();

        //relacionar tutor condicho usuario guardado
        var myRefTutor = database.getReference("tutores").push();
        myRefTutor.child("alumno_id").setValue(tutor.alumno?.key);
        myRefTutor.child("user_id").setValue(user_id);








    }
}