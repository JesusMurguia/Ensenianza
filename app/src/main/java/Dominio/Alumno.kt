package Dominio

import java.io.Serializable

class Alumno :Serializable{
    var nombre: String = ""
    var lastname: String = ""
    var key: String? = ""
    var curso_id: String? = ""

    constructor(nombre: String, lastname: String) {
        this.nombre = nombre;
        this.lastname = lastname;
    }

    constructor(key: String) {
        this.key = key;

    }

    constructor(nombre: String, lastname: String, cursoId: String, key: String) {
        this.nombre = nombre;
        this.lastname = lastname;
        this.curso_id = cursoId;
        this.key = key;
    }



    constructor() {}


}

