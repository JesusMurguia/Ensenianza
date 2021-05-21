package apps.moviles.ensenianza

import Dominio.Usuario
import Negocio.Factory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_login.btnRegistro
import kotlinx.android.synthetic.main.activity_pantalla_login_2.*
import java.io.File
import java.util.*


class PantallaLogin_2 : AppCompatActivity(), Observer {
    var isClick: Boolean = false;
    lateinit var tipo: String
    var isRemember: Boolean = false;

    var isMtro: Boolean = false;
    var isTutor: Boolean = false;
    var isUsuario: Boolean = false;
    lateinit var usuario: Usuario;

    lateinit var ediTextEmail: EditText;
    lateinit var ediTextPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_login_2)


        //crear fachada
        var fachadaNegocio = Factory.crearFachadaNegocio();

        //added obseerver
        fachadaNegocio.addObserver(this);

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            tipo = bundle.getString("tipo").toString()
        }

        btnRegistro.setOnClickListener() {
            startActivity(Intent(this, PantallaRegistrarteOptions::class.java))
        }

        //obtener datos de edittexts
        ediTextEmail = findViewById(R.id.et_nombre);
        ediTextPassword = findViewById(R.id.et_password);

        llenarCampos();


        btnIngresar.setOnClickListener() {


            if (!isClick) {

                isClick = true;
                //obtener datos de edittexts



                if (ediTextEmail.text.isEmpty() && ediTextPassword.text.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Favor de ingresar correo electronico y contraseña",
                        Toast.LENGTH_SHORT
                    ).show()
                    isClick = false;
                } else if (ediTextEmail.text.isEmpty()) {
                    Toast.makeText(this, "Favor de ingresar correo electronico", Toast.LENGTH_SHORT)
                        .show()
                    isClick = false;
                } else if (ediTextPassword.text.isEmpty()) {
                    Toast.makeText(this, "Favor de ingresar contraseña", Toast.LENGTH_SHORT).show()
                    isClick = false;
                } else {

                    //object
                    this.usuario =
                        Usuario(ediTextEmail.text.toString(), ediTextPassword.text.toString());

                    if (tipo == "profesor") {
                        isMtro = true;
                        isTutor = false;
                        fachadaNegocio.isMtroOrTutor(this, this.usuario,"maestro");


                    } else if (tipo == "padre") {
                        isMtro = false;
                        isTutor = true;
                        fachadaNegocio.isMtroOrTutor(this, usuario,"tutor");


                    }

                }


            }
        }


        checkBox_recordar.setOnClickListener({
            if (checkBox_recordar.isChecked) {
                isRemember = true;

            } else {
                isRemember = false;


            }
        })
    }

    override fun update(p0: Observable?, p1: Any?) {
        var isSuccessful: Boolean? = p1.toString().toBoolean();

        //crear fachada
        var fachadaNegocio = Factory.crearFachadaNegocio();

        fachadaNegocio.addObserver(this)
        if (isSuccessful == true && isUsuario == false) {
            isUsuario = true;

            if (isUsuario) {
                fachadaNegocio.iniciarSesionUsuario(this, usuario);
            }


        } else if (isSuccessful == true && isUsuario == true) {
            recordarUsuario(usuario);
            var i: Intent? = null;
            if (isMtro) {
                val sp = getSharedPreferences("isMtroOrTutor", Context.MODE_PRIVATE);
                sp.edit().putString("isMtroOrTutor", "maestro").apply();

                i = Intent(this, PantallaPrincipalMaestro::class.java)
                i?.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                this.finish()
            } else if (isTutor) {
                val sp = getSharedPreferences("isMtroOrTutor", Context.MODE_PRIVATE);
                sp.edit().putString("isMtroOrTutor", "tutor").apply();


                i = Intent(this, PantallaPrincipal::class.java)
                i?.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                this.finish()
            }

        }

        if (isSuccessful == false) {
            Toast.makeText(
                this,
                "Ingresar credenciales validad",
                Toast.LENGTH_SHORT
            ).show()
            isUsuario = false;
            isClick = false;
        }


    }


    fun recordarUsuario(usuario: Usuario) {


        if (isRemember == true) {
            if(tipo=="profesor"){
                val sp = getSharedPreferences("loginProfesor", Context.MODE_PRIVATE);
                sp.edit().putString("email", usuario.email).apply();
                sp.edit().putString("password", usuario.password).apply();
                sp.edit().putString("tipoUsuario", tipo).apply();
                sp.edit().putString("isRemember", isRemember.toString()).apply();


            }else if(tipo=="padre")
            {
                val sp = getSharedPreferences("loginPadre", Context.MODE_PRIVATE);
                sp.edit().putString("email", usuario.email).apply();
                sp.edit().putString("password", usuario.password).apply();
                sp.edit().putString("tipoUsuario", tipo).apply();
                sp.edit().putString("isRemember", isRemember.toString()).apply();



            }


        }
    }


    fun llenarCampos() {






            if(tipo=="profesor"){
                val f = File(
                    "/data/data/apps.moviles.ensenianza/shared_prefs/loginProfesor.xml"
                )
                if(f.exists()){
                    val sp = getSharedPreferences("loginProfesor", Context.MODE_PRIVATE);
                    var email = sp.getString("email", "").toString();
                    var password = sp.getString("password", "").toString();
                    ediTextEmail.setText(email);
                    ediTextPassword.setText(password);
                }else{

                }


            }else if(tipo=="padre"){
                val f = File(
                    "/data/data/apps.moviles.ensenianza/shared_prefs/loginPadre.xml"
                )
                if(f.exists()) {
                    val sp = getSharedPreferences("loginPadre", Context.MODE_PRIVATE);
                    var email = sp.getString("email", "").toString();
                    var password = sp.getString("password", "").toString();
                    ediTextEmail.setText(email);
                    ediTextPassword.setText(password);
                }
            }



        }


    }

