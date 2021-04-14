package apps.moviles.enseanza

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_menu.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class PantallaMenu: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu);



        //menu back
        menu_btnMenu.setOnClickListener {
            startActivity(Intent(this, PantallaPrincipal::class.java))
        }

        //btn cerrar sesion
        menu_icon_cerrar_sesion.setOnClickListener {
            startActivity(Intent(this, PantallaLogin::class.java))
        }
    }
}