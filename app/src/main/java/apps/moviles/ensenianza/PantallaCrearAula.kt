package apps.moviles.ensenianza

import Dominio.Curso
import Dominio.Usuario
import Negocio.FachadaNegocio
import Negocio.Factory
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class PantallaCrearAula : AppCompatActivity(),Observer {

    //crear fachada
    lateinit var fachadaNegocio: FachadaNegocio;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_crear_aula)


        fachadaNegocio = Factory.crearFachadaNegocio();

        //gui
        var txt_nombreProfesor=findViewById(R.id.txt_nombreProfesor) as TextView;
        var nombre_aula=findViewById(R.id.nombre_clase) as EditText;
        var descripcion=findViewById(R.id.descripcion) as EditText;
        var btn_crear=findViewById(R.id.btn_crear_asignacion) as ImageButton;



        var maestro=intent.getParcelableExtra("usuario") as Usuario;
        var name_lastname="  Mtro. "+maestro.nombre+" "+maestro.lastname+"  ";
        txt_nombreProfesor.setText(name_lastname);

        btn_crear.setOnClickListener {

            if (nombre_aula.text.toString().isEmpty()&&!descripcion.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar el nombre del aula..",Toast.LENGTH_SHORT).show();
            }else
            if (descripcion.text.toString().isEmpty()&&!nombre_aula.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar descripcion..",Toast.LENGTH_SHORT).show();
            }else
            if(nombre_aula.text.toString().isEmpty()&&descripcion.text.toString().isEmpty()){
                Toast.makeText(this,"Favor de ingresar datos..",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,"creando aula..",Toast.LENGTH_SHORT).show();
                fachadaNegocio.addObserver(this)
                fachadaNegocio.crearAula(maestro.key.toString(), Curso(nombre_aula.text.toString(),descripcion.text.toString()));



            }

        }

    }

    override fun update(p0: Observable?, p1: Any?) {
        var ready =p1 as String

        if(ready.equals("listo")){
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }
    }
}