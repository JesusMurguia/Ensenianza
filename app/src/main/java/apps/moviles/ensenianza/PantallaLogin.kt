package apps.moviles.ensenianza

import Dominio.Usuario
import Negocio.Factory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_login.*
import java.util.*


class PantallaLogin : AppCompatActivity(){

    var isOkey = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_login)

        //crear fachada
        val fachadaNegocio = Factory.crearFachadaNegocio()


        //metodo para saber si ya esta logiado
        val isSignedIn = fachadaNegocio.isSignedIn()

        if (isSignedIn) {

            val sp = getSharedPreferences("isMtroOrTutor", Context.MODE_PRIVATE);
            var isMtroOrTutor = sp.getString("isMtroOrTutor", "").toString();
            if(isMtroOrTutor.equals("maestro")){
                startActivity(Intent(this, PantallaPrincipalMaestro::class.java))
                this.finish();
            }else if(isMtroOrTutor.equals("tutor")){
                startActivity(Intent(this, PantallaPrincipal::class.java))
                this.finish();
            }

        }






        btnPadres.setOnClickListener() {
            var intent = Intent(this, PantallaLogin_2::class.java)
            intent.putExtra("tipo", "padre")
            startActivity(intent)
        }
        btnProfesor.setOnClickListener() {
            var intent = Intent(this, PantallaLogin_2::class.java)
            intent.putExtra("tipo", "profesor")
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