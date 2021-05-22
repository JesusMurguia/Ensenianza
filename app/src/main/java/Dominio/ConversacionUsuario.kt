package Dominio

import java.io.Serializable

class ConversacionUsuario : Serializable {
    lateinit var conversacion: Conversacion;
    lateinit var usuario: Usuario;

    constructor(conversdacion:Conversacion) {
        this.conversacion=conversacion;
        this.usuario=usuario;
    }

    constructor() {}

}
