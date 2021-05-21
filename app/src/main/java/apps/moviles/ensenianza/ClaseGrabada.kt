package apps.moviles.ensenianza
import android.os.Parcel
import android.os.Parcelable

class ClaseGrabada : Parcelable{

    var fecha: String?=""
    var thumbnail: Int=-1


    constructor(){

    }



    constructor(source:Parcel):this() {
        fecha = source.readString()
        thumbnail = source.readInt()
    }

    constructor(fecha: String?,thumbnail: Int,) {
        this.fecha = fecha
        this.thumbnail = thumbnail
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(fecha)
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