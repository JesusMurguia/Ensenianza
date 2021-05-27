package Dominio

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import kotlin.collections.ArrayList


@JsonIgnoreProperties(ignoreUnknown=true)
class Curso:Serializable{
    lateinit var nombre: String
    lateinit var code: String
    lateinit var descripcion: String
    lateinit var id: String
    lateinit var clases: ArrayList<Clase>
    var icono: Int = -1


    constructor(nombre: String,descripcion:String) {
        this.nombre = nombre
        this.descripcion=descripcion;
    }


    constructor()


}