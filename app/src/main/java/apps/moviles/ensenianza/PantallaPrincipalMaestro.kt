package apps.moviles.ensenianza

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.moviles.ensenianza.RecyclerAdapter
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import java.util.*


class PantallaPrincipalMaestro : AppCompatActivity() {
    var clases = ArrayList<Clase>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_maestro)

        var recyclerNuevaAsignacion: RecyclerView? = null
        recyclerNuevaAsignacion = findViewById(R.id.print_recycler_view_nueva_asignacion);

        //crear array de datos para las clases
        cargarClases();

        //horizontal layout
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //asignar layout al recycler view
        recyclerNuevaAsignacion.layoutManager = layoutManager;
        recyclerNuevaAsignacion.adapter = RecyclerAdapter(clases, View.OnClickListener {
            Toast.makeText(
                applicationContext,
                "has seleccionado la claseeeee: " + clases.get(
                    recyclerNuevaAsignacion.getChildAdapterPosition(
                        it
                    )
                ).nombreClase,
                Toast.LENGTH_SHORT
            ).show();
            var intent = Intent(this, PantallaClaseDetalle::class.java)
            startActivity(intent)
        });
        recyclerNuevaAsignacion.itemAnimator = DefaultItemAnimator();

        //llevar al menu
        prin_btnMenu.setOnClickListener() {
            startActivityForResult(Intent(this, PantallaMenu::class.java), 1)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            this.finish()
        }
    }

    fun cargarClases() {
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Ingles", "Mtra. Ana Marquez", R.drawable.ingleswhite))
        clases.add(Clase("Ciencias Naturales", "Mtra. Ana Marquez", R.drawable.cienciasmateria))
        clases.add(Clase("Español", "Mtra. Ana Marquez", R.drawable.libroespaniolwhite))
        clases.add(Clase("Civica", "Mtra. Ana Marquez", R.drawable.civicawhite))
        clases.add(Clase("Geografia", "Mtra. Ana Marquez", R.drawable.geografiawhite))
        clases.add(Clase("Ingles", "Mtra. Ana Marquez", R.drawable.ingleswhite))
        clases.add(Clase("Ciencias Naturales", "Mtra. Ana Marquez", R.drawable.cienciasmateria))
        clases.add(Clase("Español", "Mtra. Ana Marquez", R.drawable.libroespaniolwhite))
        clases.add(Clase("Civica", "Mtra. Ana Marquez", R.drawable.civicawhite))
    }
}