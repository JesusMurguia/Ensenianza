package apps.moviles.enseanza

import Dominio.Usuario
import Negocio.Factory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_login.btnRegistro
import kotlinx.android.synthetic.main.activity_pantalla_login_2.*
import java.util.*

class PantallaLogin_2 : AppCompatActivity(),Observer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_login_2)

        //crear fachada
        var fachadaNegocio= Factory.crearFachadaNegocio();

        //added obseerver
        fachadaNegocio.addObserver(this);



        btnRegistro.setOnClickListener(){
            startActivity(Intent(this, PantallaRegistrarteOptions::class.java))
        }
        btnIngresar.setOnClickListener(){




            //obtener datos de edittexts
            var ediTextEmail:EditText=findViewById(R.id.et_nombre);
            var ediTextPassword:EditText=findViewById(R.id.et_password);

            if(ediTextEmail.text.isEmpty() && ediTextPassword.text.isEmpty()){
                Toast.makeText(this,"Llena todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                //object
                var usuario= Usuario(ediTextEmail.text.toString(),ediTextPassword.text.toString());

                fachadaNegocio.iniciarSesionUsuario(this,usuario);
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