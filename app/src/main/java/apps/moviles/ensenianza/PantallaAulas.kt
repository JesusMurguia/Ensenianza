package apps.moviles.ensenianza

import Dominio.Curso
import Dominio.Maestro
import Dominio.Usuario
import Negocio.Factory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_aulas.*
import kotlinx.android.synthetic.main.recyclerview_cursos.view.*
import java.util.*
import kotlin.collections.ArrayList

class PantallaAulas : AppCompatActivity(), Observer {

    var adapter: ClasesAdapterAulas? = null
    var cursos = ArrayList<Curso>()
    var maestro = Maestro();
    var usuario = Usuario();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_aulas)

        var fachadaNegocio = Factory.crearFachadaNegocio();

        usuario = intent.getSerializableExtra("usuario") as Usuario;

        //  var cursos = intent.getParcelableArrayExtra("cursos") as ArrayList<Curso>

        this.maestro.email = usuario.email;
        this.maestro.key = usuario.key
        this.maestro.nombre = usuario.nombre;
        this.maestro.lastname = usuario.lastname;
        this.maestro.cursos = cursos;

        fachadaNegocio.addObserver(this)
        fachadaNegocio.getAulas(this.maestro.key.toString())


    }


    fun cargarAulas() {

        adapter = ClasesAdapterAulas(this, this.maestro.cursos, this.usuario);
        gridview_aulas.adapter = adapter;
    }

    override fun update(p0: Observable?, p1: Any?) {
        var cursos = p1 as ArrayList<Curso>;
        this.maestro.cursos = cursos;
        cargarAulas();
    }


}


class ClasesAdapterAulas : BaseAdapter {
    var cursos = ArrayList<Curso>()
    var contexto: Context? = null
    var usuario = Usuario();

    constructor(contexto: Context, cursos: ArrayList<Curso>, usuario: Usuario) {
        this.contexto = contexto
        this.cursos = cursos
        this.usuario = usuario;
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var curso = Curso();
        curso=cursos[p0]
        var inflador =
            contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflador.inflate(R.layout.recyclerview_cursos, null)
        vista.prin_nombre_clase.setText(curso.nombre);
        vista.prin_icono.setImageResource(cursos[p0].icono);
        vista.rlt_lyt.setOnClickListener {
            Toast.makeText(contexto, cursos[p0].nombre, Toast.LENGTH_SHORT).show()
            var intent = Intent(contexto, PantallaCrearClase::class.java)
            curso = cursos[p0];
            intent.putExtra("curso_id", curso.id);
            intent.putExtra("usuario", usuario)
            contexto!!.startActivity(intent)
        }


        return vista
    }

    override fun getItem(p0: Int): Any {
        return cursos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return cursos.size
    }
}