package apps.moviles.ensenianza


import Dominio.Alumno
import Dominio.Clase
import Dominio.Tutor
import Negocio.FachadaNegocio
import Negocio.Factory
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import java.util.*
import kotlin.collections.ArrayList


class PantallaPrincipal : AppCompatActivity(), Observer {

    var materias_id = ArrayList<String>()
    var clases = ArrayList<Clase>();
    var tutoriales = ArrayList<Tutorial>();
    var getTutor = false;

    lateinit var tutor: Tutor;

    //crear fachada
    lateinit var fachadaNegocio: FachadaNegocio;

    //GUI
    lateinit var nombre_alumno: TextView;
    lateinit var nombre_clase: TextView;
    lateinit var curso_id: String
    lateinit var curso_key: String
    lateinit var maestro: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pantalla_principal);

        var bundle=intent.extras
        if(bundle!=null){
            curso_id=bundle.get("curso_id") as String
            curso_key=bundle.get("curso_key") as String
        }

        //inicializar GUI
        nombre_alumno = findViewById(R.id.prin_tutor_nombre_alumno);
        nombre_clase = findViewById(R.id.prin_tutor_grado_grupo);


        var recyclerTutorial: RecyclerView? = null
        // asignar recycler

        recyclerTutorial = findViewById(R.id.print_recycler_view_tutorias);

        fachadaNegocio = Factory.crearFachadaNegocio();

        fachadaNegocio.addObserver(this);

        cargarInformacionPersonal();




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

        floatingActionButton.setOnClickListener(){
            intent=Intent(this, PantallaUnirseAClase::class.java)
            intent.putExtra("alumnoKey", tutor.alumno?.key)
            startActivity(intent)
        }


    }

    fun cargarProfesor() {
        var rootRef = FirebaseDatabase.getInstance()
        var cursosfb = rootRef.getReference("cursos/${tutor.alumno!!.curso_key}")

        cursosfb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                        var user_id=dataSnapshot.child("maestro_id").getValue(String::class.java).toString()
                        println(user_id)

                        var usuariosfb = rootRef.getReference("users/${user_id}")
                        usuariosfb.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.

                                var nombre=dataSnapshot.child("nombre").getValue(String::class.java).toString()
                                var lastname=dataSnapshot.child("lastname").getValue(String::class.java).toString()
                                println(nombre)
                                println(lastname)
                                maestro= "$nombre $lastname"
                                cargarClases()


                            }
                            override fun onCancelled(error: DatabaseError) {
                                // Failed to read value
                                Log.w("CodigoClases", "Failed to read value.", error.toException())
                            }

                        })


            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("CodigoClases", "Failed to read value.", error.toException())
            }

        })






    }


    fun cargarClases() {


        var fetched=false
        var rootRef = FirebaseDatabase.getInstance()
        println("aaaaaaaaa"+tutor.alumno!!.curso_key)
        var cursosfb = rootRef.getReference("cursos/${tutor.alumno!!.curso_key}")
        cursosfb.child("materias_id").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (i in dataSnapshot.children) {
                    var materia_id=i.child("materia_id").getValue(String::class.java).toString()
                    materias_id.add(materia_id)
                    fetched=true
                }
                if(!fetched){
                    Toast.makeText(applicationContext,"No se encontró el curso",Toast.LENGTH_SHORT).show()
                }
                var actividades_id=ArrayList<String>()
                var materiasfb = rootRef.getReference("materias")
                materiasfb.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        for (i in dataSnapshot.children) {

                            for (j in materias_id){
                                if(i.key.toString()==j){
                                    println(i.key)
                                    var c= Clase(i.child("nombre").getValue(String::class.java).toString(),i.child("descripcion").getValue(String::class.java).toString(),maestro,R.drawable.clasewhite,i.key.toString())


                                    for (a in i.child("actividades_id").children){
                                        actividades_id.add(a.child("actividad_id").getValue(String::class.java).toString())
                                        println(a.child("actividad_id").getValue(String::class.java).toString())
                                    }
                                    clases.add(c)
                                    break
                                }
                            }
                        }
                        var recycler: RecyclerView? = null
                        recycler = findViewById(R.id.print_recycler_view_clases);
                        //horizontal layout
                        var layoutManager: LinearLayoutManager =
                            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false);
                        var idclase:Clase
                        //asignar layout al recycler view
                        recycler.layoutManager = layoutManager;
                        recycler.adapter = RecyclerAdapter(clases, View.OnClickListener {
                            idclase=clases[recycler.getChildAdapterPosition(it)]
                            Toast.makeText(
                                applicationContext,
                                "has seleccionado la claseeeee: " + clases.get(recycler.getChildAdapterPosition(it)).nombre,
                                Toast.LENGTH_SHORT
                            ).show();
                            var intent = Intent(applicationContext, PantallaClaseDetalle::class.java)
                            intent.putExtra("ids",actividades_id)
                            intent.putExtra("clase",idclase)
                            startActivity(intent)
                        });
                        recycler.itemAnimator = DefaultItemAnimator();

                        if(!fetched){
                            Toast.makeText(applicationContext,"No se encontró el curso",Toast.LENGTH_SHORT).show()
                        }


                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        Log.w("CodigoClases", "Failed to read value.", error.toException())
                    }

                })

            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("CodigoClases", "Failed to read value.", error.toException())
            }

        })






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
        fachadaNegocio.getTutor(email);


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
            println("aaaaaaaaaaaaaa"+this.tutor.alumno!!.curso_id)

            //si no tiene curso se manda directo a la pantalla unirse a cursos
            if(this.tutor.alumno!!.curso_id==""){
                intent=Intent(this, PantallaUnirseAClase::class.java)
                intent.putExtra("alumnoKey", tutor.alumno?.key)
                startActivity(intent)
            }



            cargarProfesor()

        }

    }


}
