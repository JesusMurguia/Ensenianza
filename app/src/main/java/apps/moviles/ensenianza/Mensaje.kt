package apps.moviles.ensenianza

import android.os.Parcel
import android.os.Parcelable

class Mensaje : Parcelable{

    var from: String?=""
    var to: String?=""
    var mensaje: String?=""


    constructor(){

    }



    constructor(source:Parcel):this() {
        from = source.readString()
        to = source.readString()
        mensaje = source.readString()
    }

    constructor(from: String?, to: String?,mensaje: String?,) {
        this.from = from
        this.to = to
        this.mensaje = mensaje
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(from)
        p0?.writeString(to)
        p0?.writeString(mensaje)
    }

    companion object CREATOR : Parcelable.Creator<Mensaje> {
        override fun createFromParcel(parcel: Parcel): Mensaje {
            return Mensaje(parcel)
        }

        override fun newArray(size: Int): Array<Mensaje?> {
            return arrayOfNulls(size)
        }
    }
}