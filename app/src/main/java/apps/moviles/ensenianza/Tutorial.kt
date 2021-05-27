package apps.moviles.ensenianza

import Dominio.Clase
import android.os.Parcel
import android.os.Parcelable

class Tutorial : Parcelable{

    var nombreClase: String?=""
    var nombreTutoria: String?=""
    var thumbnail: Int=-1


    constructor(){

    }



    constructor(source:Parcel):this() {
        nombreClase = source.readString()
        nombreTutoria = source.readString()
        thumbnail = source.readInt()
    }

    constructor(nombreClase: String?, nombreTutoria: String?,thumbnail: Int,) {
        this.nombreClase = nombreClase
        this.nombreTutoria = nombreTutoria
        this.thumbnail = thumbnail
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombreClase)
        p0?.writeString(nombreTutoria)
        p0?.writeInt(thumbnail)
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