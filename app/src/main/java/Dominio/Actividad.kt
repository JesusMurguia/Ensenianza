package Dominio

import android.os.Parcel
import android.os.Parcelable
class Actividad : Parcelable{

    var nombre: String?=""

    var titulo: String?=""
    var subtitulo: String?=""

    var fechaInicio: String?=""
    var fechaEntrega: String?=""
    var id:String?=""
    var descripcion:String?=""


    constructor(){

    }



    constructor(source:Parcel):this() {
        nombre = source.readString()
        fechaInicio = source.readString()
        fechaEntrega= source.readString()
        id=source.readString()
        descripcion=source.readString()
        titulo= source.readString()
        subtitulo= source.readString()
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


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(fechaInicio)
        p0?.writeString(fechaEntrega)
        p0?.writeString(id)
        p0?.writeString(descripcion)
        p0?.writeString(titulo)
        p0?.writeString(subtitulo)
    }

    companion object CREATOR : Parcelable.Creator<Actividad> {
        override fun createFromParcel(parcel: Parcel): Actividad {
            return Actividad(parcel)
        }

        override fun newArray(size: Int): Array<Actividad?> {
            return arrayOfNulls(size)
        }
    }
}