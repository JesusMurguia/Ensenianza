package apps.moviles.enseanza

import Dominio.Tutor
import Dominio.Usuario
import Negocio.Factory
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_login.*
import kotlinx.android.synthetic.main.activity_pantalla_login.btnRegistro
import kotlinx.android.synthetic.main.activity_pantalla_login_2.*
import java.util.*

class PantallaLogin_2 : AppCompatActivity(),Observer {
    var isClick:Boolean=false;

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


            if(!isClick){
                isClick=true;
                //obtener datos de edittexts
                var ediTextEmail:EditText=findViewById(R.id.editTextTextEmail);
                var ediTextPassword:EditText=findViewById(R.id.editTextTextPassword);



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
           this.finish()

        }else{
            isClick=false;
        }


    }


}