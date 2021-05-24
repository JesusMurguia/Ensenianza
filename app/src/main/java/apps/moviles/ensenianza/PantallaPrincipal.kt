package apps.moviles.ensenianza


import Dominio.Alumno
import Dominio.Tutor
import Negocio.FachadaNegocio
import Negocio.Factory
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import java.util.*


class PantallaPrincipal : AppCompatActivity(), Observer {


    var clases = ArrayList<Clase>();
    var tutoriales = ArrayList<Tutorial>();
    var getTutor = false;

    lateinit var tutor: Tutor;

    //crear fachada
    lateinit var fachadaNegocio: FachadaNegocio;

    //GUI
    lateinit var nombre_alumno: TextView;
    lateinit var nombre_clase: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pantalla_principal);

        //inicializar GUI
        nombre_alumno = findViewById(R.id.prin_tutor_nombre_alumno);
        nombre_clase = findViewById(R.id.prin_tutor_grado_grupo);

        var recycler: RecyclerView? = null
        var recyclerTutorial: RecyclerView? = null
        // asignar recycler
        recycler = findViewById(R.id.print_recycler_view_clases);
        recyclerTutorial = findViewById(R.id.print_recycler_view_tutorias);

        fachadaNegocio = Factory.crearFachadaNegocio();

        fachadaNegocio.addObserver(this);

        cargarInformacionPersonal();

        //crear array de datos para las clases
        cargarClases()

        //horizontal layout
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //asignar layout al recycler view
        recycler.layoutManager = layoutManager;
        recycler.adapter = RecyclerAdapter(clases, View.OnClickListener {
            Toast.makeText(
                applicationContext,
                "has seleccionado la claseeeee: " + clases.get(recycler.getChildAdapterPosition(it)).nombreClase,
                Toast.LENGTH_SHORT
            ).show();
            var intent = Intent(this, PantallaClaseDetalle::class.java)
            startActivity(intent)
        });
        recycler.itemAnimator = DefaultItemAnimator();

        //crear array de datos para los tutoriales
        cargartutoriales();

        var layoutManagerTurial: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerTutorial.layoutManager = layoutManagerTurial;
        recyclerTutorial.adapter = RecyclerAdapterTutoriales(tutoriales, View.OnClickListener {
            Toast.makeText(
                applicationContext,
                "has seleccionado el tutorial de: " + tutoriales.get(
                    recyclerTutorial.getChildAdapterPosition(it)
                ).nombreClase,
                Toast.LENGTH_SHORT
            ).show();
            var intent = Intent(this, PantallaVideo::class.java)
            startActivity(intent)
        });
        recyclerTutorial.itemAnimator = DefaultItemAnimator();

        //botones

        //llevar a pantalla donde estan las clases grabadas
        prin_btn_claseGrabadas.setOnClickListener() {
            startActivity(Intent(this, PantallaClasesGrabadas::class.java))
        }

        //llevar al menu
        prin_btnMenu.setOnClickListener() {
            startActivityForResult(Intent(this, PantallaMenu::class.java), 1)
        }


        //levarte ala pantlla donde se encuentran las tareas
        prin_btn_tareas.setOnClickListener() {
            var intent = Intent(this, PantallaTarea::class.java)
            startActivity(intent)
        }

        //levarte donde estan los tutos
        print_recycler_view_tutorias.setOnClickListener() {
            startActivity(Intent(this, PantallaTutoriales::class.java))
        }


        //levarte donde estan las tareas
        print_recycler_view_clases.setOnClickListener() {
            startActivity(Intent(this, PantallaTutoriales::class.java))
        }

        //mesnajes
        prin_btn_mensajes.setOnClickListener {
            intent=Intent(this, PantallaMensajes::class.java)
            intent.putExtra("tutor",tutor)
            startActivity(intent)
        }

        //perfil

        prin_btn_perfil.setOnClickListener {

            var intent = Intent(this, PantallaPerfil::class.java);
            intent.putExtra("Tutor", this.tutor)
            startActivity(intent);
        }

    }

    fun cargarClases() {
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Ingles", "Mtra. Ana Marquez", R.drawable.ingleswhite))
        clases.add(Clase("Ciencias Naturales", "Mtra. Ana Marquez", R.drawable.cienciasmateria))
        clases.add(Clase("Español", "Mtra. Ana Marquez", R.drawable.libroespaniolwhite))
        clases.add(Clase("Civica", "Mtra. Ana Marquez", R.drawable.civicawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Ingles", "Mtra. Ana Marquez", R.drawable.ingleswhite))
        clases.add(Clase("Ciencias Naturales", "Mtra. Ana Marquez", R.drawable.cienciasmateria))
        clases.add(Clase("Español", "Mtra. Ana Marquez", R.drawable.libroespaniolwhite))
        clases.add(Clase("Civica", "Mtra. Ana Marquez", R.drawable.civicawhite))
    }

    fun cargartutoriales() {
        tutoriales.add(Tutorial("Geografia", "Mi estado", R.drawable.thumbnail5))
        tutoriales.add(Tutorial("Español", "Signos de puntuacion", R.drawable.thumbnail6))
        tutoriales.add(Tutorial("Matematicas", "Integrales", R.drawable.thumbnail5))
        tutoriales.add(Tutorial("Geografia", "Nacionalidades", R.drawable.thumbnail4))
    }


    fun cargarInformacionPersonal() {
        var email = fachadaNegocio.getEmail();

        //obtener info completa del tutor y key de su hijo para despues buscar info completa del alumno con dicha key
        fachadaNegocio.getTutor(this, email);


    }

    override fun update(p0: Observable?, p1: Any?) {
        if (getTutor == false) {
            this.tutor = p1 as Tutor;
            getTutor = true;


            //obtener info completa del alumno con su key
            fachadaNegocio.getAlumno(this, this.tutor.alumno?.key.toString());
        } else if (getTutor == true) {
            this.tutor.alumno = p1 as Alumno;
            var nombre_apellido = this.tutor.alumno!!.nombre + " " + tutor.alumno!!.lastname
            this.nombre_alumno.setText(nombre_apellido);
            this.nombre_clase.setText(this.tutor.alumno!!.curso_id);
            println("aaaaaaaaaaaaaa"+this.tutor.alumno!!.key)

        }

    }


}
