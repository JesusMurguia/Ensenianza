package apps.moviles.ensenianza

import Dominio.Usuario
import Negocio.FachadaNegocio
import Negocio.Factory
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_pantalla_principal.prin_btnMenu
import kotlinx.android.synthetic.main.activity_pantalla_principal_maestro.*
import java.util.*


class PantallaPrincipalMaestro : AppCompatActivity(), Observer {

    var clases = ArrayList<Clase>();

    //crear fachada
    lateinit var fachadaNegocio: FachadaNegocio;

    //gui floatin button exp
    val rotateOpen:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    val rotateClosed:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_closed_anim) }
    val from:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.from_button_anim) }
    val to:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.to_buttom_anim) }

    private var clicked=false;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_maestro)

        var recyclerNuevaAsignacion: RecyclerView? = null
        recyclerNuevaAsignacion = findViewById(R.id.print_recycler_view_nueva_asignacion);

        fachadaNegocio = Factory.crearFachadaNegocio();

        fachadaNegocio.addObserver(this);
        //crear array de datos para las clases
        cargarClases();

        //horizontal layout
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //asignar layout al recycler view
        recyclerNuevaAsignacion.layoutManager = layoutManager;
        recyclerNuevaAsignacion.adapter = RecyclerAdapter(clases, View.OnClickListener {
            Toast.makeText(
                applicationContext,
                "has seleccionado la claseeeee: " + clases.get(
                    recyclerNuevaAsignacion.getChildAdapterPosition(
                        it
                    )
                ).nombreClase,
                Toast.LENGTH_SHORT
            ).show();
            var intent = Intent(this, PantallaClaseDetalle::class.java)
            startActivity(intent)
        });
        recyclerNuevaAsignacion.itemAnimator = DefaultItemAnimator();

        //cargar info principal
        cargarInformacionPersonal();


        //llevar al menu
        prin_btnMenu.setOnClickListener() {
           startActivityForResult(Intent(this, PantallaMenu::class.java), 1)

        }

        //btn floatin +
        prin_mtro_floatingBtn_mas.setOnClickListener() {
         onAddBtnClicked();

        }

        //btn floatin + clases
        prin_mtro_floatingBtn_mas_clase.setOnClickListener() {
           // startActivityForResult(Intent(this, PantallaMenu::class.java), 1)
            Toast.makeText(this,"agregar mas clases..",Toast.LENGTH_LONG).show();
        }
        //btn floatin + aulas
        prin_mtro_floatingBtn_mas_aula.setOnClickListener() {
           // startActivityForResult(Intent(this, PantallaMenu::class.java), 1)
            Toast.makeText(this,"agregar mas aulas..",Toast.LENGTH_LONG).show();
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

    fun cargarInformacionPersonal() {
        var email = fachadaNegocio.getEmail();
        fachadaNegocio.getMtro(email);


    }

    override fun update(p0: Observable?, p1: Any?) {
        var usuario = p1 as Usuario;
        var nombreMtro: TextView = findViewById(R.id.prin_nombre_mtro);
        nombreMtro.setText(usuario?.nombre+" "+usuario.lastname);

    }

    private fun onAddBtnClicked(){
        setVisibility(clicked);
        setAnimation(clicked);
        setClicked(clicked);
       clicked=!clicked

    }

    private fun setVisibility(clicked:Boolean) {
        if(!clicked){
            prin_mtro_floatingBtn_mas_clase.visibility=View.VISIBLE
            prin_mtro_floatingBtn_mas_aula.visibility=View.VISIBLE;
        }else{
            prin_mtro_floatingBtn_mas_clase.visibility=View.INVISIBLE
            prin_mtro_floatingBtn_mas_aula.visibility=View.INVISIBLE;
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked){
            prin_mtro_floatingBtn_mas_clase.startAnimation(from);
            prin_mtro_floatingBtn_mas_aula.startAnimation(from);
            prin_mtro_floatingBtn_mas.startAnimation(rotateOpen);

        }else{
            prin_mtro_floatingBtn_mas_clase.startAnimation(to);
            prin_mtro_floatingBtn_mas_aula.startAnimation(to);
            prin_mtro_floatingBtn_mas.startAnimation(rotateClosed);
        }
    }

    private fun setClicked(clicked: Boolean){
        if(!clicked){

            prin_mtro_floatingBtn_mas_clase.isClickable=true
            prin_mtro_floatingBtn_mas_aula.isClickable=true
        }else{
            prin_mtro_floatingBtn_mas_clase.isClickable=false
            prin_mtro_floatingBtn_mas_aula.isClickable=false
        }
    }



}