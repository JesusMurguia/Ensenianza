package apps.moviles.enseanza

import Dominio.Alumno
import Dominio.Tutor
import Negocio.Factory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_login.*
import kotlinx.android.synthetic.main.activity_pantalla_login.btnRegistro
import kotlinx.android.synthetic.main.activity_pantalla_login_2.*
import kotlinx.android.synthetic.main.activity_pantalla_registrate.*
import java.util.*

class PantallaRegistrate : AppCompatActivity(),Observer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrate);

        //crear fachada
        var fachadaNegocio=Factory.crearFachadaNegocio();



        btnRegistrate.setOnClickListener(){

            //obtener alumno
            var alumno=Alumno("marthea","sepa")



            //obtener tutor
            var tutor=Tutor("chayo","cebreros","chayo@gmail.com",alumno);

            //registrar tutor
            fachadaNegocio.registrarTutor(this,tutor,"passsword");






        }
    }

    override fun update(p0: Observable?, p1: Any?) {
        var isSuccessful:Boolean?=p1.toString().toBoolean();

        if (isSuccessful == true){
            startActivity(Intent(this, PantallaRecordarUsuario::class.java))
        }


    }

}