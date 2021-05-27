package apps.moviles.ensenianza

import Dominio.Clase
import Dominio.Curso
import Negocio.Factory
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_clase.view.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*
import java.util.*
import kotlin.collections.ArrayList

class PantallaClases : AppCompatActivity(), Observer {
    var adapter: ClasesAdapter? = null
    var clases = ArrayList<Clase>()
    lateinit var curso: Curso;
    var   clasesAux=ArrayList<Clase>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_clases);

        var fachadaNegocio = Factory.crearFachadaNegocio();

        fachadaNegocio.addObserver(this);
        var mtro=""
        var bundle = intent.extras
        if (bundle != null) {
            clasesAux = bundle.get("clases") as ArrayList<Clase>
            mtro=bundle.get("nombreMtro") as String
        }

        for (j in clasesAux) {
            fachadaNegocio.getClase(j.idClase,mtro)

        }

    }


    fun cargarClases() {

        adapter = ClasesAdapter(this, clases);
        gridview.adapter = adapter;
    }

    override fun update(p0: Observable?, p1: Any?) {
      var clase =p1 as Clase
        clases.add(clase)
        if(clases.size==clasesAux.size){
            cargarClases();
        }
    }
}

class ClasesAdapter : BaseAdapter {
    var clases = ArrayList<Clase>()
    var contexto: Context? = null

    constructor(contexto: Context, clases: ArrayList<Clase>) {
        this.contexto = contexto
        this.clases = clases
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var clase = clases[p0]
        var inflador =
            contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflador.inflate(R.layout.activity_pantalla_clase, null)
        vista.tv_nombreClase.setText(clase.nombre)
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