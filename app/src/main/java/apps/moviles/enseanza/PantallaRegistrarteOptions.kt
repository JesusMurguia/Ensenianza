package apps.moviles.enseanza

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_login.*
import kotlinx.android.synthetic.main.activity_pantalla_registrarte_options.*

class PantallaRegistrarteOptions : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrarte_options);

        options_btnPadres.setOnClickListener() {


            startActivity(Intent(this, PantallaRegistrate::class.java))
        }

        options_btnProfesor.setOnClickListener() {


            startActivity(Intent(this, PantallaRegistrarteMaestro::class.java))
        }




    }
}