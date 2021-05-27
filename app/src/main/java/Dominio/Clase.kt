package Dominio

import android.os.Parcel
import android.os.Parcelable
import apps.moviles.ensenianza.R

class Clase : Parcelable {

    var nombreClase: String? = ""
    var nombreProfesor: String? = ""
    var icono: Int = -1
    var idClase: String? = ""

    constructor(clase_id: String) {
        this.idClase = clase_id
        this.icono= R.drawable.clasewhite;
    }

    constructor() {

    }


    constructor(source: Parcel) : this() {
        nombreClase = source.readString()
        nombreProfesor = source.readString()
        icono = source.readInt()
    }

    constructor(nombreClase: String?, nombreProfesor: String?, icono: Int) {
        this.nombreClase = nombreClase
        this.nombreProfesor = nombreProfesor
        this.icono = icono
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombreClase)
        p0?.writeString(nombreProfesor)
        p0?.writeInt(icono)
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