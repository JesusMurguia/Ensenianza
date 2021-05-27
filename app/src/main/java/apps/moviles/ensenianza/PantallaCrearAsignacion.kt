package apps.moviles.ensenianza

import Dominio.Actividad
import Dominio.Clase
import Dominio.Curso
import Negocio.Factory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_crear_asignacion.*

class PantallaCrearAsignacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_crear_asignacion)

        //gui
        var txt_nombreProfesor=findViewById(R.id.et_nombreProfe) as TextView;
        var et_nombreMateria=findViewById(R.id.et_nombreMateria) as TextView;
        var titulo=findViewById(R.id.titulo) as EditText;
        var subtitulo=findViewById(R.id.subtitulo) as EditText;
        var descripcion=findViewById(R.id.descripcion) as EditText;

        var  curso=Curso();
        var clase=Clase();

        var bundle = intent.extras
        if (bundle != null) {
              clase = bundle.get("clase") as Clase
              curso = bundle.get("curso") as Curso
            txt_nombreProfesor.setText(clase.nombreProfesor);
            et_nombreMateria.setText(clase.nombre);

        }


        btn_crear_asignacion.setOnClickListener {


            if(titulo.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar el titulo de la asignacion..", Toast.LENGTH_SHORT).show();
            }else if(descripcion.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar la descripcion de la asignacion..", Toast.LENGTH_SHORT).show();
            }else if(subtitulo.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar el subtitulo de la asignacion..", Toast.LENGTH_SHORT).show();
            }else if(titulo.text.toString().isEmpty()&&descripcion.text.toString().isEmpty()&&subtitulo.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar datos..", Toast.LENGTH_SHORT).show();

            }else{
                var asignacion=Actividad(titulo.text.toString(),subtitulo.text.toString(),descripcion.text.toString())
                var fachadaNegocio = Factory.crearFachadaNegocio();
                clase.idClase?.let { it1 -> fachadaNegocio.crearAsignacion(it1, asignacion) };
               this.finish();
            }




        }
    }
}