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
import kotlinx.android.synthetic.main.activity_pantalla_clase_grabada.view.*
import kotlinx.android.synthetic.main.activity_pantalla_tutorial.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensaje.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*
import kotlinx.android.synthetic.main.activity_pantalla_tutorial.view.thumbnail

class PantallaTutoriales : AppCompatActivity() {
    var adapter: tutorialesAdapter? = null
    var tutoriales = ArrayList<Tutorial>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_tutoriales)
        cargartutoriales();


        adapter=tutorialesAdapter(this, tutoriales)
        gridview.adapter=adapter
    }
    fun cargartutoriales(){
        tutoriales.add(Tutorial("Geografia", "Mi estado", R.drawable.thumbnail5))
        tutoriales.add(Tutorial("Español", "Signos de puntuacion", R.drawable.thumbnail6))
        tutoriales.add(Tutorial("Matematicas", "Integrales", R.drawable.thumbnail5))
        tutoriales.add(Tutorial("Geografia", "Nacionalidades", R.drawable.thumbnail4))
    }
}

class tutorialesAdapter: BaseAdapter {
    var tutoriales= ArrayList<Tutorial>()
    var contexto: Context? =null
    constructor(contexto: Context, tutoriales: ArrayList<Tutorial>){
        this.contexto=contexto
        this.tutoriales=tutoriales
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var tutorial=tutoriales[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pantalla_tutorial, null)
        vista.tv_nombreTutoria.setText(tutorial.nombreTutoria)
        vista.tv_nombreClase.setText(tutorial.nombreClase)
        vista.thumbnail.setImageResource(tutorial.thumbnail)
        //vista.setBackgroundResource(R.drawable.rounded_edit_text)
        vista.thumbnail.setOnClickListener(){
            var intent= Intent(contexto, PantallaVideo::class.java)
            contexto!!.startActivity(intent)
        }
        return vista
    }

    override fun getItem(p0: Int): Any {
        return tutoriales[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return tutoriales.size
    }
}