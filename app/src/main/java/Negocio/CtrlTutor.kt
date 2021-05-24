package Negocio

import Dominio.Alumno
import Dominio.Tutor
import Dominio.Usuario
import android.util.Log
import android.widget.Toast
import apps.moviles.ensenianza.PantallaRegistrate
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*


class CtrlTutor : Observable() {

    lateinit var tutor: Tutor;
    fun cerrarSesion(): Boolean {
        return true
    }

    fun subirTarea(): Boolean {
        return true
    }

    fun registrarTutor(activity: PantallaRegistrate, tutor: Tutor) {


        var isSuccessful: Boolean? = false;

        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //inicializar fachada negocio
        //crear fachada
        var fachadaNegocio = Factory.crearFachadaNegocio();

        mAuth.createUserWithEmailAndPassword(tutor.email, tutor.password)
            .addOnCompleteListener(activity,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {

                        val user = mAuth.currentUser


                        Toast.makeText(
                            activity,
                            "Se ha registrado correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()


                        //--guardar alumno
                        var alumno_id = fachadaNegocio.registrarAlumno(tutor.alumno);

                        //set key al alumno
                        tutor.alumno?.key = alumno_id;


                        //--guardar usuario
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("users").push();

                        myRef.child("nombre").setValue(tutor.nombre);
                        myRef.child("lastname").setValue(tutor.lastname);
                        myRef.child("email").setValue(tutor.email);

                        var user_id = myRef.key.toString();

                        //--relacionar tutor condicho usuario guardado
                        var myRefTutor = database.getReference("tutores").push();
                        myRefTutor.child("alumno_id").setValue(tutor.alumno?.key);
                        myRefTutor.child("user_id").setValue(user_id);

                        isSuccessful = true

                        //patron observer
                        setChanged();
                        notifyObservers(isSuccessful);
                        clearChanged();
                    } else {

                        Toast.makeText(
                            activity,
                            "Authentication failed." + task.exception,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }
                })


    }

    fun getTutor(emailTutor: String) {


        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.reference

        // query para obtener tutor(usuario)
        var user = ref.child("users").orderByChild("email").equalTo(emailTutor);
        user.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var data: Tutor?=null;
                var key=""

                for (i in snapshot.children){
                    data = i.getValue(Tutor::class.java)!!
                    key=i.key.toString();
                }

                 tutor = Tutor(
                    data?.nombre.toString(),
                    data?.lastname.toString(),
                    data?.email.toString()

                )

                getIdAlumno(key);

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CtrlTutor", "Failed to read value.", error.toException())
            }


        })


    }


    /**
     * este metodo obtiene el id del alumno de un tutor
     */
    fun getIdAlumno(id_tutor: String) {

        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.reference


        var tutorQuery = ref.child("tutores").orderByChild("user_id").equalTo(id_tutor);

        tutorQuery.addListenerForSingleValueEvent(object : ValueEventListener {



            override fun onDataChange(snapshot: DataSnapshot) {



                var key_alumno=""
                for (i in snapshot.children){
                     key_alumno = i.child("alumno_id").getValue(String::class.java).toString();
                }

                var alumno = Alumno(key_alumno);
                tutor.alumno = alumno;


                setChanged();
                notifyObservers(tutor);
                clearChanged();

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CtrlTutor", "Failed to read value.", error.toException())
            }

        })
    }


}