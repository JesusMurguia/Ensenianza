package Negocio

import Dominio.Maestro
import Dominio.Usuario
import android.widget.Toast
import apps.moviles.ensenianza.PantallaRegistrarteMaestro
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class CtrlMaestro : Observable() {


    fun registrarMtro(activity: PantallaRegistrarteMaestro, maestro: Maestro) {


        var isSuccessful: Boolean? = false;

        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //inicializar fachada negocio
        var fachadaNegocio = Factory.crearFachadaNegocio();


        mAuth.createUserWithEmailAndPassword(maestro.email, maestro.password)
            .addOnCompleteListener(activity,
                OnCompleteListener<AuthResult?> { task ->

                    if (task.isSuccessful) {

                        val user = mAuth.currentUser


                        Toast.makeText(
                            activity,
                            "Se ha registrado correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()


                        //--guardar usuario
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("users").push();

                        myRef.child("nombre").setValue(maestro.nombre);
                        myRef.child("lastname").setValue(maestro.lastname);
                        myRef.child("email").setValue(maestro.email);

                        var user_id = myRef.key.toString();

                        //--relacionar tutor condicho usuario guardado
                        var myRefTutor = database.getReference("maestros").push();
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

    fun getMtro(emailMaestro: String): Maestro? {

        var maestro: Maestro? =null;
        // Initialize Firebase Auth

        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.reference
        var user = ref.child("users").orderByChild("email").equalTo(emailMaestro);
        user.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var data = snapshot.getValue(Usuario::class.java);

                maestro = Maestro(
                    data?.nombre.toString(),
                    data?.lastname.toString(),
                    data?.email.toString()
                )

                setChanged();
                notifyObservers(maestro);
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




        return maestro;
    }

}