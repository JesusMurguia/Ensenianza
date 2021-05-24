package Negocio

import Dominio.Maestro
import Dominio.Usuario
import android.util.Log
import android.widget.Toast
import apps.moviles.ensenianza.PantallaRegistrarteMaestro
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class CtrlMaestro : Observable() {

    /**
     * este metodo registra a un mtro al sitema
     */
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

    /**
     * este metodo obtiene la key del mtro de la tabla maestros por medio del user_id, cabe mencionar que no regresa el user_id del mtro
     */
    fun getKey(user_id:String){
        var rootRef = FirebaseDatabase.getInstance()

        var ref = rootRef.getReference("maestros").orderByChild("user_id").equalTo(user_id);
        //  var maestro = ref.orderByChild("user_id").equalTo(user_id);
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var key =""
                for (i in snapshot.children){
                    key=i.key.toString();
                }

                setChanged();
                notifyObservers(key);
                clearChanged();
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CtrlMaestro", "Failed to read value.", error.toException())
            }


        })

    }




}