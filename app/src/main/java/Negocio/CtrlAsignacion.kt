package Negocio

import Dominio.Actividad
import Dominio.Asignacion
import com.google.firebase.database.FirebaseDatabase

class CtrlAsignacion {



    fun crearAsignacion(materia_id:String,actividad: Actividad){

       var database = FirebaseDatabase.getInstance()

        //se ingresa curso a la tabla materias
        val myRef = database.getReference("actividades").push();


        myRef.child("titulo").setValue(actividad.titulo);
        myRef.child("subtitulo").setValue(actividad.subtitulo);
        myRef.child("descripcion").setValue(actividad.descripcion);
        var keyAsignacion= myRef.key.toString();

        val myRefCurso = database.getReference("materias/${materia_id}").child("actividades_id").push()
        myRefCurso.child("actividad_id").setValue(keyAsignacion);


    }

}