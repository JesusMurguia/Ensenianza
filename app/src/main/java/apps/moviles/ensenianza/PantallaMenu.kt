package apps.moviles.ensenianza

import Negocio.Factory
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_menu.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class PantallaMenu: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu);

        //crear fachada
        val fachadaNegocio = Factory.crearFachadaNegocio()


        //menu back
        menu_btnMenu.setOnClickListener {
            startActivity(Intent(this, PantallaPrincipal::class.java))
        }

        //btn cerrar sesion
        menu_icon_cerrar_sesion.setOnClickListener {
            fachadaNegocio.cerrarSesion()
            this.finish()
            startActivity(Intent(this, PantallaLogin::class.java))
        }
    }
}