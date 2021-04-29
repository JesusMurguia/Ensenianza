package Dominio

import java.util.*

class Maestro(
    nombre: String?,
    apellido: String?,
    conversacionesUsuarios: ArrayList<ConversacionUsuario>,
    var materiasMaestros: ArrayList<MateriaMaestro>
) : Usuario(nombre, apellido, conversacionesUsuarios)