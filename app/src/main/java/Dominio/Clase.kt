package Dominio

import android.os.Parcel
import android.os.Parcelable
import apps.moviles.ensenianza.R
import java.io.Serializable

class Clase : Parcelable {


    var nombre: String? = ""
    var nombreProfesor: String? = ""
    var icono: Int = -1
    var idClase: String? = ""
    lateinit var actividades:ArrayList<Asignacion>;


    constructor(clase_id: String) {
        this.idClase = clase_id
        this.icono= R.drawable.clasewhite;
    }

    constructor() {

    }


    constructor(source: Parcel) : this() {
        nombre = source.readString()
        nombreProfesor = source.readString()
        icono = source.readInt()
        idClase= source.readString()
    }


    constructor(nombreClase: String?, nombreProfesor: String?,icono: Int,idclase:String?) {

        this.nombre = nombreClase
        this.nombreProfesor = nombreProfesor
        this.icono = icono
        this.idClase=idclase
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(nombreProfesor)
        p0?.writeInt(icono)
        p0?.writeString(idClase)
    }

    companion object CREATOR : Parcelable.Creator<Clase> {
        override fun createFromParcel(parcel: Parcel): Clase {
            return Clase(parcel)
        }

        override fun newArray(size: Int): Array<Clase?> {
            return arrayOfNulls(size)
        }
    }
}