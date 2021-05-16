package apps.moviles.enseanza

import Dominio.Alumno
import Dominio.Tutor
import Negocio.Factory
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            try{
            if(et_password.text.toString().equals(et_confirmpassword.text.toString())) {
                //obtener alumno
                var alumno = Alumno(et_nombre.text.toString(),et_apellido.text.toString())


                //obtener tutor
                var tutor =
                    Tutor(et_nombrePadre.text.toString()  , et_apellidoPadre.text.toString(), et_correo.text.toString(), et_password.text.toString()
                        , alumno);

                //registrar tutor
                fachadaNegocio.registrarTutor(this, tutor);


            }else{
                Toast.makeText(this,"Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }catch (e:IllegalArgumentException){
            Toast.makeText(this,"Llena todos los campos",Toast.LENGTH_SHORT).show()
        }
        }
    }

    override fun update(p0: Observable?, p1: Any?) {
        var isSuccessful:Boolean?=p1.toString().toBoolean();

        if (isSuccessful == true){
            startActivity(Intent(this, PantallaRecordarUsuario::class.java))
        }


    }

}