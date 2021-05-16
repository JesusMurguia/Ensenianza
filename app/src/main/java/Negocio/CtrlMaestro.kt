package Negocio

import Dominio.Maestro
import Dominio.Usuario
import android.util.Log
import android.widget.Toast
import apps.moviles.enseanza.PantallaLogin_2
import apps.moviles.enseanza.PantallaRegistrarteMaestro
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class CtrlMaestro: Observable() {



    fun registrarTutor(activity: PantallaRegistrarteMaestro, maestro: Maestro) {




        var isSuccessful: Boolean? = false;

        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //inicializar fachada negocio
        var fachadaNegocio=Factory.crearFachadaNegocio();

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
                        myRef.child("lastname").setValue(maestro.apellido);
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

                        Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()

                    }
                })


    }


    fun isMtro(activity: PantallaLogin_2, usuario: Usuario): Boolean {

        val database = FirebaseDatabase.getInstance()
        val users = database.getReference("users");
        val maestros = database.getReference("maestros");

        var isMtro:Boolean=false
        var key:String=""
        users.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (i in dataSnapshot.children) {

                    //obtener nombre(se peude obtener mas elementos)
                    var value = i.child("email").getValue(String::class.java)
                    if(usuario.email.equals(value)){
                        key =i.key.toString();
                        Log.d("PantallaLogin", "La clave del men es: $key")
                        break
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("PantallaLogin", "Failed to read value.", error.toException())
            }
        })
        maestros.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (i in dataSnapshot.children) {

                    //obtener nombre(se peude obtener mas elementos)
                    var value = i.child("user_id").getValue(String::class.java)
                    if(value.equals(key)) {

                        isMtro=true

                    }
                }
                if(isMtro){
                    setChanged();
                    notifyObservers(isMtro);
                    clearChanged();
                }else {
                    Toast.makeText(activity,"Este usuario no es maestro.",Toast.LENGTH_SHORT).show()
                    setChanged();
                    notifyObservers(false);
                    clearChanged();
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("PantallaLogin", "Failed to read value.", error.toException())
            }
        })





        return false
    }
}