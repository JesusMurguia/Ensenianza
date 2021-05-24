package apps.moviles.ensenianza

import android.os.Parcel
import android.os.Parcelable

class MensajeMaestro : Parcelable{

    var from: String?=""
    var to: String?=""
    var mensaje: String?=""
    var conversacion: String?=""


    constructor(){

    }



    constructor(source:Parcel):this() {
        from = source.readString()
        to = source.readString()
        mensaje = source.readString()
        conversacion= source.readString()
    }

    constructor(from: String?, to: String?,mensaje: String?,conversacion:String?) {
        this.from = from
        this.to = to
        this.mensaje = mensaje
        this.conversacion=conversacion
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(from)
        p0?.writeString(to)
        p0?.writeString(mensaje)
        p0?.writeString(conversacion)
    }

    companion object CREATOR : Parcelable.Creator<MensajeMaestro> {
        override fun createFromParcel(parcel: Parcel): MensajeMaestro {
            return MensajeMaestro(parcel)
        }

        override fun newArray(size: Int): Array<MensajeMaestro?> {
            return arrayOfNulls(size)
        }
    }
}