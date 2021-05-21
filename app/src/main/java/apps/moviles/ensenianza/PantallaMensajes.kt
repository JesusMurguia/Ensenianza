package apps.moviles.ensenianza

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_mensaje.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.view.*
import kotlinx.android.synthetic.main.activity_pantalla_perfil.*

class PantallaMensajes : AppCompatActivity() {
    var adapter: MensajesAdapter? = null
    var mensajes = ArrayList<Mensaje>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_mensajes)

        cargarMensajes();


        adapter=MensajesAdapter(this, mensajes)
        gridview.adapter=adapter
    }

    fun cargarMensajes(){
        mensajes.add(Mensaje("El Chapo","Ana Guzman","Que onda sobrina"))
        mensajes.add(Mensaje("el profe","Ana Guzman","reprobaste xd"))
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