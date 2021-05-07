package Negocio

import android.content.Context

class CtrlTutor {

  //  private var accesoDatos:FachadaDatos;
    constructor(){
     //   accesoDatos=FabricaDatos.crearFachadaDatos();
    }

    fun iniciarSesion(context: Context?,usuario:String?,password:String?): Boolean? {

       // var token= accesoDatos.iniciarSesion(context,usuario,password);
        return true;

    }

    fun cerrarSesion(): Boolean {
        return true
    }

    fun subirTarea(): Boolean {
        return true
    }
}