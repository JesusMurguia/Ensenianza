package Negocio

import Dominio.Alumno
import Dominio.Tutor
import com.google.firebase.database.FirebaseDatabase

class CtrlAlumno {

    fun registrarAlumno(alumno: Alumno?):String? {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("alumnos").push();

        myRef.child("nombre").setValue(alumno?.nombre);
        myRef.child("lastname").setValue(alumno?.lastname);
        myRef.child("curso_id").setValue("");




        return myRef.key.toString();

    }


}