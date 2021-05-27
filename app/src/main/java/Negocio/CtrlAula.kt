package Negocio

import Dominio.Curso
import Dominio.Materia
import android.util.Log
import Dominio.Clase
import apps.moviles.ensenianza.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class CtrlAula : Observer, Observable {

    /**
     * metodo para crear un aula, este metodo ingresa un aula ala tabla cursos y busca
     * la key del mtro
     */


    lateinit var negocio: CtrlMaestro;
    lateinit var keyCurso: String;
    lateinit var database: FirebaseDatabase


    //variables para poder obtener las aulas y los ids de sus clases
     var noAulas=0;
    var noClases=0;
    var isLoadGetAulas=true;



    var cursos: ArrayList<Curso>;


    constructor() {

        cursos = ArrayList<Curso>();
    }

    fun crearAula(userId: String, curso: Curso) {

        database = FirebaseDatabase.getInstance()

        //crear un curso nuevo, se ingresa curso a la tabla cursos
        val myRef = database.getReference("cursos").push();
        myRef.child("nombre").setValue(curso.nombre);
        myRef.child("descripcion").setValue(curso.descripcion);
        myRef.child("maestro_id").setValue(userId);
        this.keyCurso = myRef.key.toString();
        myRef.child("codigo").setValue(  this.keyCurso.substring(this.keyCurso.length-5));

        //ctrlMaestro para poder tener la key del mtro
        this.negocio = CtrlMaestro();
        this.negocio.addObserver(this)

        //obtener key del mtro
        negocio.getKey(userId);


    }


    /**
     * obtiene los cursos o aulas(es lo mismo) de un mtro, lo hace usando el user_id del mtro;
     */
    fun getAulas(user_id: String) {

        var rootRef = FirebaseDatabase.getInstance()
        var ref = rootRef.getReference("maestros").orderByChild("user_id").equalTo(user_id);

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                //for para obtener el maestro
                for (i in snapshot.children) {


                    var cursos_id = i.child("cursos_id")
                    var curso_id = "";

                    //for para obtener los cursos del mtro encontrado
                    for (j in cursos_id.children) {
                        curso_id = j.child("curso_id").getValue(String::class.java).toString();
                        getAulaId(curso_id)
                        noAulas=1+noAulas;

                    }

                }



                isLoadGetAulas=false

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CtrlAula", "Failed to read value.", error.toException())
            }

        })

    }

    /**
     * este metodo sirve para buscar un aula por medio de su key
     */
    fun getAulaId(aula_id: String) {
        var rootRef = FirebaseDatabase.getInstance()
        var ref = rootRef.getReference("cursos/${aula_id}");


        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var data: Curso? = null;
                var materias: Materia? = null;

                data = snapshot.getValue(Curso::class.java)!!;
                data.icono= R.drawable.aula;
                data.id = snapshot.key.toString();


                var materias_id = snapshot.child("materias_id")


                //lista de clases del curso encontrado
                var clases = ArrayList<Clase>();
                //for para obtener los cursos del mtro encontrado
                for (j in materias_id.children) {
                    var curso_id = j.child("materia_id").getValue(String::class.java).toString();
                    clases.add(Clase(curso_id))


                }
                noClases=1+noClases;
                data.clases = clases;
                cursos.add(data)

                if(noAulas==noClases && isLoadGetAulas==false){
                    setChanged();
                    notifyObservers(cursos);
                    clearChanged();

                    noClases=0
                    noAulas=0
                    isLoadGetAulas=true;
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CtrlAula", "Failed to read value.", error.toException())
            }

        })
    }


    override fun update(p0: Observable?, p1: Any?) {
        if (p0 == negocio) {
            var keyMtro = p1 as String
            val myRefMtro = this.database.getReference("maestros/${keyMtro}");
            myRefMtro.child("cursos_id").push().child("curso_id").setValue(this.keyCurso);
var code=this.keyCurso.substring(this.keyCurso.length-5);
            setChanged();
            notifyObservers(code);
            clearChanged();
        }
    }
}