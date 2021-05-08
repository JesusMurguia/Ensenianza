package apps.moviles.enseanza

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pantalla_login.*


class PantallaLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_login);





        btnPadres.setOnClickListener() {


            startActivity(Intent(this, PantallaLogin_2::class.java))
        }
        btnProfesor.setOnClickListener() {
            startActivity(Intent(this, PantallaLogin_2::class.java))
        }
        btnRegistro.setOnClickListener() {
            startActivity(Intent(this, PantallaRegistrarteOptions::class.java))
        }
        btnHome.setOnClickListener() {
            startActivity(Intent(this, PantallaVideo::class.java))
        }
    }
}