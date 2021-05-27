package apps.moviles.ensenianza

import Dominio.Clase
import Dominio.Curso
import Dominio.Tutor
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_pantalla_clase.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*

class PantallaClases : AppCompatActivity() {
    var adapter: ClasesAdapter? = null
    var clases = ArrayList<Clase>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_clases);
        cargarClases();

        var bundle=intent.extras
        if(bundle!=null){
            var curso=bundle.get("curso") as Curso
        }



        adapter=ClasesAdapter(this, clases);
        gridview.adapter=adapter;
    }


    fun cargarClases(){
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
    }
}

class ClasesAdapter: BaseAdapter {
    var clases= ArrayList<Clase>()
    var contexto: Context? =null
    constructor(contexto: Context, clases: ArrayList<Clase>){
        this.contexto=contexto
        this.clases=clases
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var clase=clases[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pantalla_clase, null)
        vista.tv_nombreClase.setText(clase.nombreClase)
        vista.tv_nombreProfesor.setText(clase.nombreProfesor)
        vista.icono.setImageResource(clase.icono)
        vista.setBackgroundResource(R.drawable.rounded_edit_text)

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