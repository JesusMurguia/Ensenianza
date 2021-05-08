package apps.moviles.enseanza

import Dominio.Maestro
import Dominio.Tutor
import Negocio.Factory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_registrarte_maestro.*
import kotlinx.android.synthetic.main.activity_pantalla_registrarte_options.*
import kotlinx.android.synthetic.main.activity_pantalla_registrarte_options.options_btnPadres
import java.util.*

class PantallaRegistrarteMaestro : AppCompatActivity(),Observer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrarte_maestro)

        //crear fachada
        var fachadaNegocio= Factory.crearFachadaNegocio();

        //added obseerver
        fachadaNegocio.addObserver(this);

        btnRegistrate_maestro.setOnClickListener() {


            //obtener maestro
            var maestro= Maestro("adminMtro  ","adminApellido","adminMtro@gmail.com","admin123");
            fachadaNegocio.registrarMaestro(this,maestro);
        }

    }

    override fun update(p0: Observable?, p1: Any?) {
        var isSuccessful:Boolean?=p1.toString().toBoolean();

        if (isSuccessful == true){
            startActivity(Intent(this, PantallaRecordarUsuario::class.java))
        }


    }
}