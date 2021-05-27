package apps.moviles.ensenianza

import Dominio.Actividad
import Dominio.Clase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_entregar_asignacion.*

class PantallaEntregarAsignacion : AppCompatActivity() {
    lateinit var clase: Clase
    lateinit var actividad:Actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_entregar_asignacion);

        var bundle=intent.extras
        if(bundle!=null){
            clase=bundle.get("clase") as Clase
            actividad=bundle.get("actividad") as Actividad

            et_nombreAsignacion.text=actividad.nombre
            et_nombreMateria.text=clase.nombre
            et_nombreProfe.text=clase.nombreProfesor
            et_asigacion_id.text=clase.idClase
            et_descripcion.text=actividad.descripcion
            iv_icono.setImageResource(clase.icono)
        }

    }
}