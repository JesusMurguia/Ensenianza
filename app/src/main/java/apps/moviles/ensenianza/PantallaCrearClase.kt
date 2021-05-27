package apps.moviles.ensenianza

import Dominio.Clase
import Dominio.Usuario
import Negocio.FachadaNegocio
import Negocio.Factory
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class PantallaCrearClase : AppCompatActivity(), Observer {

    //crear fachada
    lateinit var fachadaNegocio: FachadaNegocio;

    var  usuario=Usuario();
    var  curso_id="";

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_crear_clase)



        fachadaNegocio = Factory.crearFachadaNegocio();

        //gui
        var txt_nombreProfesor = findViewById(R.id.txt_nombreProfesor) as TextView;
        var nombre_aula = findViewById(R.id.nombre_clase) as EditText;
        var descripcion = findViewById(R.id.descripcion) as EditText;
        var btn_crear = findViewById(R.id.btn_crear_asignacion) as ImageButton;

        curso_id = intent.getStringExtra("curso_id");
          usuario = intent.getSerializableExtra("usuario") as Usuario;


        var name_lastname = "  Mtro. " + usuario.nombre + " " + usuario.lastname + "  ";
        txt_nombreProfesor.setText(name_lastname);

        btn_crear.setOnClickListener {

            if (nombre_aula.text.toString().isEmpty() && !descripcion.text.toString().isEmpty()) {
                Toast.makeText(
                    this,
                    "Favor de ingresar el nombre de la clase..",
                    Toast.LENGTH_SHORT
                ).show();
            } else
                if (descripcion.text.toString().isEmpty() && !nombre_aula.text.toString()
                        .isEmpty()
                ) {
                    Toast.makeText(this, "Favor de ingresar descripcion..", Toast.LENGTH_SHORT)
                        .show();
                } else
                    if (nombre_aula.text.toString().isEmpty() && descripcion.text.toString()
                            .isEmpty()
                    ) {
                        Toast.makeText(this, "Favor de ingresar datos..", Toast.LENGTH_SHORT)
                            .show();

                    } else {

                        Toast.makeText(this,"creando clase..",Toast.LENGTH_SHORT).show();
                        fachadaNegocio.addObserver(this)
                        var clase=Clase();
                        clase.nombre=nombre_aula.text.toString();
                        clase.descripcion=descripcion.text.toString();
                        fachadaNegocio.agregarClase(curso_id, clase);
                        this.finish();

                    }

        }
















    }

    override fun update(p0: Observable?, p1: Any?) {
        TODO("Not yet implemented")
    }
}