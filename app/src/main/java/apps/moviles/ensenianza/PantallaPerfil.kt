package apps.moviles.ensenianza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_perfil.*

class PantallaPerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_perfil)

        btnMensajes.setOnClickListener(){
            startActivity(Intent(this, PantallaMensajes::class.java))
        }
    }
}