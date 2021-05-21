package apps.moviles.ensenianza

import Negocio.Factory
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_login.*


class PantallaLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_login)

        //crear fachada
        val fachadaNegocio = Factory.crearFachadaNegocio()

        //metodo para saber si ya esta logiado
        val isSignedIn = fachadaNegocio.isSignedIn()

        if (isSignedIn) {
            startActivity(Intent(this, PantallaRecordarUsuario::class.java))
            this.finish();
        }






        btnPadres.setOnClickListener() {
            var intent=Intent(this, PantallaLogin_2::class.java)
            intent.putExtra("tipo","padre")
            startActivity(intent)
        }
        btnProfesor.setOnClickListener() {
            var intent=Intent(this, PantallaLogin_2::class.java)
            intent.putExtra("tipo","profesor")
            startActivity(intent)
        }
        btnRegistro.setOnClickListener {
            startActivity(Intent(this, PantallaRegistrarteOptions::class.java))
        }
        btnHome.setOnClickListener {
            startActivity(Intent(this, PantallaVideo::class.java))
        }
    }



}