package apps.moviles.ensenianza
import Dominio.Clase
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class ClaseGrabada : Serializable{

    var fecha: String?=""
    var thumbnail: Int=-1


    constructor(){

    }



    constructor(fecha: String?,thumbnail: Int,) {
        this.fecha = fecha
        this.thumbnail = thumbnail
    }

}