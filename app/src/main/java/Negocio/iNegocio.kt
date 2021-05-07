package Negocio

import android.content.Context

interface iNegocio {
    fun iniciarSesion(context:Context?,usuario: String?, contrasenia: String?): Boolean?
    fun cerrarSesion(): Boolean
    fun subirTarea(): Boolean
}