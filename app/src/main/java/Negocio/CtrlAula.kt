package Negocio

import Dominio.Curso
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class CtrlAula : Observer {

    /**
     * metodo para crear un aula, este metodo ingresa un aula ala tabla cursos y busca
     * la key del mtro
     */

    lateinit var negocio: CtrlMaestro;
    lateinit var keyCurso: String;
    lateinit var database:FirebaseDatabase

    fun crearAula(userId: String, curso: Curso) {

         database = FirebaseDatabase.getInstance()

        //crear un curso nuevo, se ingresa curso a la tabla cursos
        val myRef = database.getReference("cursos").push();
        myRef.child("nombre").setValue(curso.nombre);
        this.keyCurso = myRef.key.toString();


        //ctrlMaestro para poder tener la key del mtro
        this.negocio = CtrlMaestro();
        this.negocio.addObserver(this)

        //obtener key del mtro
        negocio.getKey(userId);



    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p0 == negocio) {
            var keyMtro =p1 as String
            val myRefMtro = this.database.getReference("maestros/${keyMtro}");
            myRefMtro.child("cursos_id").push().child("curso_id").setValue(this.keyCurso);
        }
    }
}