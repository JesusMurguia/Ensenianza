package apps.moviles.ensenianza

import Dominio.Maestro
import Negocio.Factory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_registrarte_maestro.*
import java.lang.Exception
import java.lang.IllegalArgumentException
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
            try{



            if(et_password.text.toString().equals(et_confirmpassword.text.toString())){
                //obtener maestro
                var maestro= Maestro(et_nombre.text.toString(),et_apellido.text.toString(),et_correo.text.toString(),et_password.text.toString());
                fachadaNegocio.registrarMaestro(this,maestro);
            }else{
                Toast.makeText(this,"Contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }
            }catch (e:IllegalArgumentException){
                Toast.makeText(this,"Llena todos los campos",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun update(p0: Observable?, p1: Any?) {
        var isSuccessful:Boolean?=p1.toString().toBoolean();

        if (isSuccessful == true){
            startActivity(Intent(this, PantallaPrincipalMaestro::class.java))
        }


    }
}