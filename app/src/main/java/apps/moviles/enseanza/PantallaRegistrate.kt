package apps.moviles.enseanza

import Dominio.Alumno
import Dominio.Tutor
import Negocio.Factory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_login.*
import kotlinx.android.synthetic.main.activity_pantalla_login.btnRegistro
import kotlinx.android.synthetic.main.activity_pantalla_login_2.*
import kotlinx.android.synthetic.main.activity_pantalla_registrate.*

class PantallaRegistrate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrate);

        //crear fachada
        var fachadaNegocio=Factory.crearFachadaNegocio();



        btnRegistrate.setOnClickListener(){

            //obtener alumno
            var alumno=Alumno("teresa","barreras")


            //registrar alumno
           var alumno_id= fachadaNegocio.registrarAlumno(alumno);

            //set key al alumno
            alumno.key=alumno_id;

            //obtener tutor
            var tutor=Tutor("luz","cebreros","cebreros@gmail.com",alumno);

            //registrar tutor
            fachadaNegocio.registrarTutor(tutor);





            startActivity(Intent(this, PantallaRecordarUsuario::class.java))
        }
    }

}