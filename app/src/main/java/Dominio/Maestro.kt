package Dominio

import java.util.*

class Maestro(
    nombre: String?,
    apellido: String?,
    email:String?,
    password:String?,
    var materiasMaestros: ArrayList<MateriaMaestro>
) : Usuario(nombre, apellido, email,password)