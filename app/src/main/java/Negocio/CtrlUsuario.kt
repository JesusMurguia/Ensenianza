package Negocio

import Dominio.Tutor
import Dominio.Usuario
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apps.moviles.ensenianza.PantallaLogin
import apps.moviles.ensenianza.PantallaLogin_2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.security.Key
import java.util.*

class CtrlUsuario : Observable {
    constructor() {

    }
    lateinit var maestros:DatabaseReference
    fun isSignedIn(): Boolean {

        var isSignedIn: Boolean = false;
        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        var user = mAuth.currentUser;

        if (user !== null) {
            isSignedIn = true;
        }
        return isSignedIn;
    }
    var isMtro:Boolean=false
    fun cerrarSesion() {


        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //cerrar sesion
        mAuth.signOut();


    }


    fun iniciarSesionUsuario(activity: PantallaLogin_2, usuario: Usuario) {

        var isSuccessful: Boolean? = false;

        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        mAuth.signInWithEmailAndPassword(usuario.email, usuario.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {

                    val user = mAuth.currentUser
                    isSuccessful = true
                    Toast.makeText(activity, "Ha Iniciado Sesion Correctamente", Toast.LENGTH_SHORT)
                        .show();
                    //patron observer
                    setChanged();
                    notifyObservers(isSuccessful);
                    clearChanged();
                } else {
                    // If sign in fails, display a message to the user.

                 //   Toast.makeText(
                   //     activity,
                   //     "Ingresar credenciales validad",
                  //      Toast.LENGTH_SHORT
                 //   ).show()
                    setChanged();
                    notifyObservers(isSuccessful);
                    clearChanged();
                }
            }


    }

    fun isMtroOrTutor(activity: AppCompatActivity, usuario: Usuario,tipo:String): Boolean {

        val database = FirebaseDatabase.getInstance()
        val users = database.getReference("users");



        if(tipo.equals("tutor")){
            maestros = database.getReference("tutores");
        }else if(tipo.equals("maestro")){
           maestros = database.getReference("maestros");
        }



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
                        break;
                    }
                }
                next(key,activity,tipo);

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("PantallaLogin", "Failed to read value.", error.toException())
            }

        })








        return false
    }

    fun next(key:String,activity: AppCompatActivity,tipo:String){
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
                    notifyObservers(true);
                    clearChanged();
                }else if(isMtro==false){
                  // Toast.makeText(activity,"Este usuario no es "+tipo+".",Toast.LENGTH_SHORT).show()
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
    }

    fun getEmail():String{
        //--registrat usuario en auth firebase
        var mAuth: FirebaseAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        return mAuth.currentUser.email;
    }

}