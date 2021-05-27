package Dominio

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import kotlin.collections.ArrayList


@JsonIgnoreProperties(ignoreUnknown=true)
class Curso:Parcelable{
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

    constructor(source: Parcel) : this() {
        nombre = source.readString().toString()
        icono = source.readInt()
        id= source.readString().toString()
        descripcion=source.readString().toString();
        clases=source.createTypedArrayList(Clase.CREATOR)!!
    }

    constructor()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(descripcion)
        p0?.writeInt(icono)
        p0?.writeString(id)
        p0?.writeTypedList(clases)
    }

    companion object CREATOR : Parcelable.Creator<Curso> {
        override fun createFromParcel(parcel: Parcel): Curso {
            return Curso(parcel)
        }

        override fun newArray(size: Int): Array<Curso?> {
            return arrayOfNulls(size)
        }
    }

}