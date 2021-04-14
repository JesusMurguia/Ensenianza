package apps.moviles.enseanza


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class PantallaPrincipal : AppCompatActivity() {


    var clases = ArrayList<Clase>();
    var tutoriales = ArrayList<Tutorial>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_pantalla_principal);
        setContentView(R.layout.activity_pantalla_principal);
        var recycler: RecyclerView? = null
        var recyclerTutorial: RecyclerView? = null
        // asignar recycler
        recycler = findViewById(R.id.print_recycler_view_clases);
        recyclerTutorial = findViewById(R.id.print_recycler_view_tutorias);

        //crear array de datos para las clases
        cargarClases();

        //horizontal layout
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //asignar layout al recycler view
        recycler.layoutManager = layoutManager;
        recycler.adapter = RecyclerAdapter(clases,View.OnClickListener {
           Toast.makeText(applicationContext,"has seleccionado la claseeeee: "+clases.get(recycler.getChildAdapterPosition(it)).nombreClase,Toast.LENGTH_SHORT).show();
            var intent= Intent(this, PantallaClaseDetalle::class.java)
            startActivity(intent)
        });
        recycler.itemAnimator = DefaultItemAnimator();

        //crear array de datos para los tutoriales
        cargartutoriales();

        var layoutManagerTurial: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerTutorial.layoutManager = layoutManagerTurial;
        recyclerTutorial.adapter = RecyclerAdapterTutoriales(tutoriales,View.OnClickListener {
            Toast.makeText(applicationContext,"has seleccionado el tutorial de: "+tutoriales.get(recyclerTutorial.getChildAdapterPosition(it)).nombreClase,Toast.LENGTH_SHORT).show();
            var intent= Intent(this, PantallaVideo::class.java)
            startActivity(intent)
        });
        recyclerTutorial.itemAnimator = DefaultItemAnimator();

        //botones

        //llevar a pantalla donde estan las clases grabadas
        prin_btn_claseGrabadas.setOnClickListener() {
            startActivity(Intent(this, PantallaClasesGrabadas::class.java))
        }




        //levarte ala pantlla donde se encuentran las tareas
        prin_btn_tareas.setOnClickListener() {
            startActivity(Intent(this, PantallaClases::class.java))
        }

//levarte donde estan los tutos
        print_recycler_view_tutorias.setOnClickListener() {
            startActivity(Intent(this, PantallaTutoriales::class.java))
        }

          //mensajes
        prin_btn_mensajes.setOnClickListener {
            startActivity(Intent(this, PantallaMensajes::class.java))
        }

        cardView2.setOnClickListener {
            startActivity(Intent(this, PantallaPerfil::class.java))
        }

        //levarte donde estan las tareas
        print_recycler_view_clases.setOnClickListener() {
            startActivity(Intent(this, PantallaTutoriales::class.java))
        }


        //mesnajes
        prin_btn_mensajes.setOnClickListener {
            startActivity(Intent(this, PantallaMensajes::class.java))
        }

        //perfil

        cardView2.setOnClickListener {
            startActivity(Intent(this, PantallaPerfil::class.java))

        //menu
        prin_btnMenu.setOnClickListener {
            startActivity(Intent(this, PantallaMenu::class.java))

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




}


