package apps.moviles.ensenianza

class ConversacionDatos {
        var nombreProfe: String? = null
        var nombreAlumno: String? = null



        // Empty constructor needed for Firestore serialization
        constructor()
        constructor(nombreProfe: String?, nombreAlumno: String?) {
            this.nombreProfe = nombreProfe
            this.nombreAlumno = nombreAlumno

        }


}