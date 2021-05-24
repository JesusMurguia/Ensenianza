package apps.moviles.ensenianza

import Dominio.Tutor
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pantalla_mensaje.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*

class PantallaMensajes : AppCompatActivity() {
    var adapter: MensajesAdapter? = null
    var mensajes = ArrayList<Mensaje>()
    lateinit var tutor: Tutor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_mensajes)

        var bundle=intent.extras
        if(bundle!=null){
            tutor=bundle.get("tutor") as Tutor
        }


        cargarMensajes();



    }

    fun cargarMensajes(){


            var rootRef = FirebaseDatabase.getInstance()

            var ref = rootRef.reference
            var maestrosid=ArrayList<String>()

            // query para obtener tutor(usuario)
            var maestrosfb = ref.child("maestros")
            maestrosfb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (i in dataSnapshot.children) {
                        var id = i.child("user_id").getValue(String::class.java)

                        maestrosid.add(id.toString())
                    }

                }



                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Conversacion", "Failed to read value.", error.toException())
                }

            })
            var maestrosNombre=ArrayList<String>()
            var users = ref.child("users")
            users.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (i in dataSnapshot.children) {
                        var nombre = i.child("nombre").getValue(String::class.java)
                        var apellido = i.child("lastname").getValue(String::class.java)
                        for(j in maestrosid){
                            if(j == i.key){
                                maestrosNombre.add(nombre + " " +apellido)
                                break
                            }
                        }

                    }
                    for (m in maestrosNombre){

                        mensajes.add(Mensaje(m,tutor.nombre + " " + tutor.lastname,"Ver Mensajes"))
                    }
                    adapter=MensajesAdapter(applicationContext, mensajes)
                    gridview.adapter=adapter

                }



                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Conversacion", "Failed to read value.", error.toException())
                }

            })


    }
}


class MensajesAdapter: BaseAdapter {
    var mensajes= ArrayList<Mensaje>()
    var contexto: Context? =null
    constructor(contexto: Context, mensajes: ArrayList<Mensaje>){
        this.contexto=contexto
        this.mensajes=mensajes
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var mensaje=mensajes[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pantalla_mensaje, null)
        vista.setOnClickListener(){
            val database = FirebaseDatabase.getInstance()

            val conversacion = database.getReference("conversaciones");

            val alumno: String? = mensaje.to
            val profesor: String? = mensaje.from
            var encontrado: Boolean = false
            var fetched = false
            var key: String = ""
            conversacion.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (i in dataSnapshot.children) {

                        //obtener nombre(se peude obtener mas elementos)
                        var nombreAlumno = i.child("nombreAlumno").getValue(String::class.java)
                        var nombreProfe = i.child("nombreProfe").getValue(String::class.java)
                        if (alumno.equals(nombreAlumno) && profesor.equals(nombreProfe)) {
                            encontrado = true
                            key = i.key.toString();
                            Log.d("Conversacion", "id de conversacion: $key")

                            break;
                        }
                        println("o no")
                    }
                    if (!encontrado) {
                        var nuevaconvo= ConversacionDatos(profesor,alumno)
                        val myRef = database.getReference("conversaciones").push();

                        myRef.child("nombreAlumno").setValue(nuevaconvo.nombreAlumno);
                        myRef.child("nombreProfe").setValue(nuevaconvo.nombreProfe);
                        key=myRef.key.toString()
                        Log.d("Conversacion", "id de conversacion nueva: $key")
                    }

                    if (!fetched) {

                        var intent = Intent(contexto, MainActivity::class.java)
                        intent.putExtra("usuario", mensaje.to)
                        intent.putExtra("maestro", mensaje.from)
                        intent.putExtra("key", key)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK
                        contexto!!.startActivity(intent)
                        fetched=true
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Conversacion", "Failed to read value.", error.toException())
                }

            })

        }
        vista.tv_mensaje.setText(mensaje.mensaje)
        vista.tv_nombre.setText(mensaje.from)


        return vista
    }

    override fun getItem(p0: Int): Any {
        return mensajes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mensajes.size
    }
}