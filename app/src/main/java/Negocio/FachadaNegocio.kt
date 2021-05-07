package Negocio


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

}