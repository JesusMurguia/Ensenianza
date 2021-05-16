package apps.moviles.enseanza

import Dominio.Alumno
import Dominio.Tutor
import Negocio.Factory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_registrate.*
import java.util.*

class PantallaRegistrate : AppCompatActivity(),Observer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrate);

        //crear fachada
        var fachadaNegocio=Factory.crearFachadaNegocio();

        //added obseerver
        fachadaNegocio.addObserver(this);


        btnRegistrate_maestro.setOnClickListener(){

            //obtener alumno
            var alumno=Alumno("hijoAdmin","apeelidoAdmin")



            //obtener tutor
            var tutor=Tutor("admin  ","adminApellido","admin@gmail.com","admin123",alumno);

            //registrar tutor
            fachadaNegocio.registrarTutor(this,tutor);



        }
    }

    override fun update(p0: Observable?, p1: Any?) {
        var isSuccessful:Boolean?=p1.toString().toBoolean();

        if (isSuccessful == true){
            startActivity(Intent(this, PantallaRecordarUsuario::class.java))
        }


    }

}