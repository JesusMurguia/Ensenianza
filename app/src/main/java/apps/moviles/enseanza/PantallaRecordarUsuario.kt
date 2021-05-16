package apps.moviles.enseanza

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_recordar_usuario.*

class PantallaRecordarUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_recordar_usuario)

        val i = Intent(this, PantallaPrincipal::class.java)
        // set the new task and clear flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        btnSi.setOnClickListener(){

            startActivity(i)
            finish()
        }
        btnMasTarde.setOnClickListener(){


            startActivity(i)
            finish()
        }





    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        super.onBackPressed()
    }

}