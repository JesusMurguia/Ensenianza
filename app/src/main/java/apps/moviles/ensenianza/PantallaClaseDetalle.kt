package apps.moviles.ensenianza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_clase_detalle.*

class PantallaClaseDetalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_clase_detalle);
        tareaUrgenteBttn.setOnClickListener(){
            var intent= Intent(this, PantallaEntregarAsignacion::class.java)
            startActivity(intent)
        }
        bttnClasesGrabadas.setOnClickListener(){
            startActivity(Intent(this, PantallaClasesGrabadas::class.java))

        }
        bttnAsesoria.setOnClickListener(){
            startActivity(Intent(this, PantallaTutoriales::class.java))

        }
        btnChat.setOnClickListener(){
            startActivity(Intent(this, PantallaMensajes::class.java))

        }
    }
}