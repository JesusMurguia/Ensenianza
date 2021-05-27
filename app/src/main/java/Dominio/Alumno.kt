package Dominio

import java.io.Serializable

class Alumno :Serializable{
    var nombre: String = ""
    var lastname: String = ""
    var key: String? = ""
    var curso_id: String? = ""
    var curso_key: String? = ""

    constructor(nombre: String, lastname: String) {
        this.nombre = nombre;
        this.lastname = lastname;
    }

    constructor(key: String) {
        this.key = key;

    }

    constructor(nombre: String, lastname: String, cursoId: String,curso_key:String, key: String) {
        this.nombre = nombre;
        this.lastname = lastname;
        this.curso_id = cursoId;
        this.curso_key=curso_key
        this.key = key;
    }



    constructor() {}


}

