package Dominio

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

@JsonIgnoreProperties(ignoreUnknown=true)
open class Usuario : Parcelable {
    var nombre: String? = null
    var lastname: String? = null
    var email: String? = null
    var password: String? = null
    //var conversacionesUsuarios: ArrayList<ConversacionUsuario>?=null;
    var key: String? = null



    constructor(
        nombre: String?, apellido: String?,email:String?,password: String?

    ) {
        this.nombre = nombre
        this.lastname = apellido
        this.email=email;
        this.password=password;

    }
    constructor(
        nombre: String?, apellido: String?,email:String?

    ) {
        this.nombre = nombre
        this.lastname = apellido
        this.email=email;


    }
    constructor(
        email:String?,password:String?

    ) {
        this.email=email;
        this.password = password


    }
    constructor()

    @JvmName("getNombre1")
    fun getNombre():String?{
        return this.nombre;
    }

    constructor(source: Parcel) : this() {
        nombre = source.readString().toString()
        lastname = source.readString().toString()
        password= source.readString().toString()
        email=source.readString().toString();
        key=source.readString().toString()
    }

    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(lastname)
        p0?.writeString(password)
        p0?.writeString(email)
        p0?.writeString(key)
    }


    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

}

annotation class JsonIgnoreProperties(val ignoreUnknown: Boolean)
