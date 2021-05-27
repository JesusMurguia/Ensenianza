package Dominio

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Actividad : Serializable{

    var nombre: String?=""

    var titulo: String?=""
    var subtitulo: String?=""

    var fechaInicio: String?=""
    var fechaEntrega: String?=""
    var id:String?=""
    var descripcion:String?=""

    constructor(){

    }

    constructor(nombre: String?, fechaInicio: String?,fechaEntrega:String?,id:String?,descripcion:String?) {
        this.nombre = nombre
        this.fechaInicio = fechaInicio
        this.fechaEntrega=fechaEntrega
        this.id=id
        this.descripcion=descripcion
    }


    constructor(titulo: String?, subtitulo: String?,descripcion: String?) {

        this.titulo = titulo
        this.subtitulo = subtitulo
        this.descripcion=descripcion
    }





}