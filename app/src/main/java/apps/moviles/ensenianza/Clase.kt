package apps.moviles.ensenianza

import android.os.Parcel
import android.os.Parcelable

class Clase : Parcelable{

    var nombreClase: String?=""
    var nombreProfesor: String?=""
    var icono: Int=-1


    constructor(){

    }



    constructor(source:Parcel):this() {
        nombreClase = source.readString()
        nombreProfesor = source.readString()
        icono = source.readInt()
    }

    constructor(nombreClase: String?, nombreProfesor: String?,icono: Int,) {
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