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
import kotlinx.android.synthetic.main.activity_pantalla_clase.view.*
import kotlinx.android.synthetic.main.activity_pantalla_clase_grabada.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*

class PantallaClasesGrabadas : AppCompatActivity() {
    var adapter: ClasesGAdapter? = null
    var clases = ArrayList<ClaseGrabada>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_clases_grabadas)
        cargarClases();


        adapter=ClasesGAdapter(this, clases)
        gridview.adapter=adapter
    }
    fun cargarClases(){
        clases.add(ClaseGrabada("Jueves 18 de Abril 2021", R.drawable.thumbnail1))
        clases.add(ClaseGrabada("Viernes 19 de Abril 2021", R.drawable.thumbnail2))
        clases.add(ClaseGrabada("Sabado 20 de Abril 2021", R.drawable.thumbnail3))
        clases.add(ClaseGrabada("Jueves 18 de Abril 2021", R.drawable.thumbnail1))
        clases.add(ClaseGrabada("Viernes 19 de Abril 2021", R.drawable.thumbnail2))
        clases.add(ClaseGrabada("Sabado 20 de Abril 2021", R.drawable.thumbnail3))
        clases.add(ClaseGrabada("Jueves 18 de Abril 2021", R.drawable.thumbnail1))
        clases.add(ClaseGrabada("Viernes 19 de Abril 2021", R.drawable.thumbnail2))
        clases.add(ClaseGrabada("Sabado 20 de Abril 2021", R.drawable.thumbnail3))
    }
}



class ClasesGAdapter: BaseAdapter {
    var clases= ArrayList<ClaseGrabada>()
    var contexto: Context? =null
    constructor(contexto: Context, clases: ArrayList<ClaseGrabada>){
        this.contexto=contexto
        this.clases=clases
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var clase=clases[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pantalla_clase_grabada, null)
        vista.fecha.setText(clase.fecha)
        vista.thumbnail.setImageResource(clase.thumbnail)
        //vista.setBackgroundResource(R.drawable.rounded_edit_text)
        vista.thumbnail.setOnClickListener(){
            var intent= Intent(contexto, PantallaVideo::class.java)
            contexto!!.startActivity(intent)
        }
        return vista
    }

    override fun getItem(p0: Int): Any {
        return clases[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return clases.size
    }
}