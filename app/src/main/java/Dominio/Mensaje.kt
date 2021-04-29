package Dominio

class Mensaje {
    var receptor: Usuario? = null
    var emisor: Usuario? = null
    var mensaje: String? = null

    constructor(receptor: Usuario?, emisor: Usuario?, mensaje: String?) {
        this.receptor = receptor
        this.emisor = emisor
        this.mensaje = mensaje
    }

    constructor() {}

}