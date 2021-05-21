
package Dominio

class Alumno{
    var nombre: String=""
    var lastname: String=""
    var key: String?=""
    var curso_id: String?=""

    constructor(nombre:String,lastname:String) {
        this.nombre=nombre;
        this.lastname=lastname;
    }
    constructor(nombre:String,lastname:String,cursoId:String) {
        this.nombre=nombre;
        this.lastname=lastname;
        this.curso_id=cursoId;
    }



}

