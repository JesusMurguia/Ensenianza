package Negocio

import Dominio.Alumno
import Dominio.Tutor
import Dominio.Usuario
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apps.moviles.ensenianza.PantallaRegistrate
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
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

    fun getTutor(activity: AppCompatActivity, emailTutor: String) {


        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.reference

        // query para obtener tutor(usuario)
        var user = ref.child("users").orderByChild("email").equalTo(emailTutor);
        user.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                var data = snapshot.getValue(Usuario::class.java);

                tutor = Tutor(
                    data?.nombre.toString(),
                    data?.lastname.toString(),
                    data?.email.toString(),

                    )



                getKeyAlumno(activity, snapshot.key.toString());


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

    fun getKeyAlumno(activity: AppCompatActivity, key: String) {
        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.reference


        var tutorQuery = ref.child("tutores").orderByChild("user_id").equalTo(key);

        tutorQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var key_alumno = snapshot.child("alumno_id").getValue(String::class.java);


                var alumno = Alumno(key_alumno.toString());
                tutor.alumno = alumno;




                setChanged();
                notifyObservers(tutor);
                clearChanged();


            }

            override fun onChildChanged(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {
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