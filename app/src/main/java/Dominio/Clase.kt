package Dominio

import android.os.Parcel
import android.os.Parcelable
import apps.moviles.ensenianza.R
import java.io.Serializable

class Clase : Serializable {


    var nombre: String? = ""
    var descripcion: String? = ""
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



    constructor(nombreClase: String?, nombreProfesor: String?,icono: Int,idclase:String?) {

        this.nombre = nombreClase
        this.nombreProfesor = nombreProfesor
        this.icono = icono
        this.idClase=idclase
    }

}