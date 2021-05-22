package Negocio

import Dominio.Alumno
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class CtrlAlumno: Observable() {

    fun registrarAlumno(alumno: Alumno?): String? {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("alumnos").push();

        myRef.child("nombre").setValue(alumno?.nombre);
        myRef.child("lastname").setValue(alumno?.lastname);
        myRef.child("curso_id").setValue("");




        return myRef.key.toString();

    }

    fun getAlumno(activity: AppCompatActivity, key: String) {



        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.reference


        var alumnoQuery = ref.child("alumnos").orderByKey().equalTo(key);

        alumnoQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var dataAlumno = snapshot.getValue(Alumno::class.java);


                var alumno = Alumno(
                    dataAlumno?.nombre.toString(),
                    dataAlumno?.lastname.toString(),
                    dataAlumno?.curso_id.toString(),
                    key
                )


                setChanged();
                notifyObservers(alumno);
                clearChanged();


            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}