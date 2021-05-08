package Negocio

import Dominio.Tutor
import Dominio.Usuario
import android.widget.Toast
import apps.moviles.enseanza.PantallaLogin_2
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class CtrlUsuario : Observable {
    constructor() {

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

                    Toast.makeText(
                        activity,
                        "password o correo estan incorrectos",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }


    }
}