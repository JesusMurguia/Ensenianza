package apps.moviles.ensenianza

import Dominio.Tutor
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_perfil.*

class PantallaPerfil : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_perfil)

        var intent = intent.extras
        if (intent != null) {
          mostrarInformacionPrincipal()
        }

        btnMensajes.setOnClickListener() {
            startActivity(Intent(this, PantallaMensajes::class.java))
        }

        perfil_btn_home.setOnClickListener {
            this.finish();
        }
    }

    fun mostrarInformacionPrincipal() {
        //GUI
        var nombre_responsable: TextView = findViewById(R.id.perfil_nombre_responsable);
        var email_responsable: TextView = findViewById(R.id.perfil_email_responsable);
        var nombre_alumno: TextView = findViewById(R.id.perfil_nombre_alumno);
        var nombre_aula: TextView = findViewById(R.id.perfil_nombre_aula);
        var desempenio: TextView = findViewById(R.id.perfil_desempenio);

        var tutor:Tutor = intent.getSerializableExtra("Tutor") as Tutor
        nombre_responsable.setText(tutor.nombre);
        email_responsable.setText(tutor.email);

        var nombre_apellido = tutor.alumno!!.nombre + " " + tutor.alumno!!.lastname
        nombre_alumno.setText(nombre_apellido);

        nombre_aula.setText(tutor.alumno?.curso_id);
        desempenio.setText(" Desempeño del alumno en ${tutor.alumno?.curso_id}")


    }
}