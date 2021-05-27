package Negocio

import Dominio.Actividad
import Dominio.Asignacion
import Dominio.Clase
import android.util.Log
import apps.moviles.ensenianza.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class CtrlClase : Observable() {


    lateinit var negocio: CtrlMaestro;
    lateinit var database: FirebaseDatabase

    /**
     * Este metodo obtiene laa clase buscando por su id
     */



    fun getClase(materia_id: String?,nombreMtro:String) {
        var asignaciones=ArrayList<Asignacion>();
        var rootRef = FirebaseDatabase.getInstance()
        var ref = rootRef.getReference("materias/${materia_id}")


        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var data: Clase? = null;

                data = snapshot.getValue(Clase::class.java)!!;
                data.idClase = materia_id;
                data.nombreProfesor=nombreMtro;
                data.icono= R.drawable.clasewhite;


                setChanged();
                notifyObservers(data);
                clearChanged();

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CtrlClase", "Failed to read value.", error.toException())
            }

        })
    }


    /**
     *
     * Este metodo asigna una clase a un curso
     *
     */
    fun agregarClase(curso_id:String,clase:Clase)
    {

        database = FirebaseDatabase.getInstance()

        //se ingresa curso a la tabla materias
        val myRef = database.getReference("materias").push();

        myRef.child("nombre").setValue(clase.nombre);
        myRef.child("descripcion").setValue(clase.descripcion);
        var keyClase= myRef.key.toString();

       val myRefCurso = database.getReference("cursos/${curso_id}").child("materias_id").push()
        myRefCurso.child("materia_id").setValue(keyClase);



    }


}