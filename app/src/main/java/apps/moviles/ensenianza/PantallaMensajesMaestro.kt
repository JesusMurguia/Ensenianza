package apps.moviles.ensenianza

import Dominio.Alumno
import Dominio.Usuario
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

class PantallaMensajesMaestro : AppCompatActivity() {
    var adapter: MensajesAdapterMaestro? = null
    var mensajes = ArrayList<MensajeMaestro>()
    var usuario: Usuario? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_mensajes)
        var bundle= intent.extras
        if(bundle!=null) {

            usuario = bundle.get("usuario") as Usuario

            //cargarMensajes(alumno!!)
        }



        val database = FirebaseDatabase.getInstance()

        val conversacion = database.getReference("conversaciones");

        val profesor: String? = usuario?.nombre + " " + usuario?.lastname
        var encontrado: Boolean = false
        var fetched = false
        var key: String = ""
        var alumnostring: String = ""
        conversacion.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (i in dataSnapshot.children) {

                    //obtener nombre(se peude obtener mas elementos)
                    var nombreProfe = i.child("nombreProfe").getValue(String::class.java)
                    var nombreAlumno = i.child("nombreAlumno").getValue(String::class.java)
                    if (profesor.equals(nombreProfe)) {
                        if (nombreAlumno != null) {
                            alumnostring=nombreAlumno
                        }
                        encontrado = true
                        key = i.key.toString();
                        Log.d("Conversacion", "id de conversacion: $key")
                        mensajes.add(MensajeMaestro(nombreAlumno,nombreProfe,"Ver conversacion",key))
                    }
                    println("o no")
                }

                if (!fetched) {


                    adapter=MensajesAdapterMaestro(applicationContext, mensajes,usuario!!,key)
                    gridview.adapter=adapter
                    /*
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("key", key)
                    applicationContext!!.startActivity(intent)
                    */

                    fetched=true
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Conversacion", "Failed to read value.", error.toException())
            }

        })





    }

}


class MensajesAdapterMaestro: BaseAdapter {
    var mensajes= ArrayList<MensajeMaestro>()
    var contexto: Context? =null
    var usuario: Usuario? =null
    var key: String?=null
    constructor(contexto: Context, mensajes: ArrayList<MensajeMaestro>, usuario: Usuario,key:String){
        this.contexto=contexto
        this.mensajes=mensajes
        this.usuario=usuario
        this.key=key
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var mensaje=mensajes[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pantalla_mensaje, null)
        vista.setOnClickListener(){

            var intent = Intent(contexto, ChatMaestro::class.java)
            intent.putExtra("key", mensaje.conversacion)
            intent.putExtra("usuario",usuario)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contexto!!.startActivity(intent)


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