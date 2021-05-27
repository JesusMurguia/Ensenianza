package apps.moviles.ensenianza

import Dominio.Clase
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Tutorial : Serializable{

    var nombreClase: String?=""
    var nombreTutoria: String?=""
    var thumbnail: Int=-1


    constructor(){

    }



    constructor(nombreClase: String?, nombreTutoria: String?,thumbnail: Int,) {
        this.nombreClase = nombreClase
        this.nombreTutoria = nombreTutoria
        this.thumbnail = thumbnail
    }

}