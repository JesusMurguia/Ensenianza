package apps.moviles.ensenianza

import Dominio.Clase
import Dominio.Curso
import Dominio.Maestro
import Dominio.Usuario
import Negocio.FachadaNegocio
import Negocio.Factory
import android.app.Activity
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
import kotlinx.android.synthetic.main.activity_pantalla_principal_maestro.prin_btn_mensajes
import java.util.*
import kotlin.collections.ArrayList


class PantallaPrincipalMaestro : AppCompatActivity(), Observer {

    var clases = ArrayList<Clase>();

    lateinit var maestro:Maestro;



    //crear fachada
    lateinit var fachadaNegocio: FachadaNegocio;

    //gui floatin button exp
    val rotateOpen:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    val rotateClosed:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_closed_anim) }
    val from:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.from_button_anim) }
    val to:Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.to_buttom_anim) }

    private var clicked=false;

    private var isLoadMtro=false;
    private var isLoadCursos=false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_maestro)



        this.maestro= Maestro();
        fachadaNegocio = Factory.crearFachadaNegocio();

        fachadaNegocio.addObserver(this);

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

        prin_btn_mensajes.setOnClickListener {
            intent=Intent(this, PantallaMensajesMaestro::class.java)
            intent.putExtra("usuario",maestro)
            startActivity(intent)
        }


        //btn floatin + clases
        prin_mtro_floatingBtn_mas_clase.setOnClickListener() {
            startActivityForResult(Intent(this, PantallaClases::class.java), 1)
            Toast.makeText(this,"agregar mas clases..",Toast.LENGTH_LONG).show();
        }
        //btn floatin + aulas
        prin_mtro_floatingBtn_mas_aula.setOnClickListener() {
            startActivityForResult(Intent(this, PantallaCrearAula::class.java).putExtra("usuario",this.maestro), 1)
            Toast.makeText(this,"agregar mas aulas..",Toast.LENGTH_LONG).show();
        }

    }


    fun cargarCursos() {
        var recyclerNuevaAsignacion: RecyclerView? = null
        recyclerNuevaAsignacion = findViewById(R.id.print_recycler_view_nueva_asignacion);

        //horizontal layout
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //asignar layout al recycler view
        recyclerNuevaAsignacion.layoutManager = layoutManager;
        recyclerNuevaAsignacion.adapter = RecyclerAdapterCursos(this.maestro.cursos, View.OnClickListener {
            Toast.makeText(
                applicationContext,
                "has seleccionado el curso: " + this.maestro.cursos.get(recyclerNuevaAsignacion.getChildAdapterPosition(it)).nombre,
                Toast.LENGTH_SHORT
            ).show();
            var intent = Intent(this, PantallaClases::class.java).putExtra("curso",this.maestro.cursos.get(recyclerNuevaAsignacion.getChildAdapterPosition(it)))
            startActivity(intent)
        });
        recyclerNuevaAsignacion.itemAnimator = DefaultItemAnimator();


    }



    fun cargarInformacionPersonal() {
        isLoadMtro=true;
        var email = fachadaNegocio.getEmail();
        fachadaNegocio.getUsuario(email);


    }

    override fun update(p0: Observable?, p1: Any?) {

        if(isLoadMtro==true){
            var usuario =p1 as Usuario;
            this.maestro.email=usuario.email;
            this.maestro.key=usuario.key
            this.maestro.nombre=usuario.nombre;
            this.maestro.lastname=usuario.lastname;

            var nombreMtro: TextView = findViewById(R.id.prin_nombre_mtro);
            var name_lastname=maestro.nombre+" "+maestro.lastname;
            nombreMtro.setText(name_lastname);
            isLoadMtro=false;
            isLoadCursos=true
            //crear array de datos para las clases

            fachadaNegocio.getAulas(this.maestro.key.toString())


        }else if(isLoadCursos==true){
            isLoadCursos=false
            var cursos=p1 as ArrayList<Curso>;
            this.maestro.cursos=cursos;
            cargarCursos();

        }


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

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        if(requestCode==1){
            if(requestCode==Activity.RESULT_OK){
                cargarCursos();
            }
        }
    }


}