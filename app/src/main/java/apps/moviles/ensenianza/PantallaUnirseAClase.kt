package apps.moviles.ensenianza

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pantalla_unirse_a_clase.*


class PantallaUnirseAClase : AppCompatActivity() {
    lateinit var codigoClase:String
    lateinit var alumnoKey:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_unirse_a_clase)
        var rootRef = FirebaseDatabase.getInstance()

        var bundle= intent.extras
        if(bundle!=null){
            alumnoKey= bundle.get("alumnoKey").toString()
        }

        var ref = rootRef.reference
        btn_unirse.setOnClickListener(){
            codigoClase=et_codigoClase.text.toString()
            var nombreCurso:String=""
            var cursosfb = ref.child("cursos")
            var fetched=false
            cursosfb.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (i in dataSnapshot.children) {
                        if(i.key?.contains(codigoClase) == true){
                            codigoClase= i.key!!
                            println("key"+i.key.toString())
                            nombreCurso=i.child("nombre").getValue(String::class.java).toString()
                            println("nombre"+nombreCurso)
                            var alumnosRef = rootRef.getReference("alumnos/${alumnoKey}")
                            alumnosRef.child("curso_id").setValue(nombreCurso)
                            fetched=true
                            intent = Intent(applicationContext, PantallaPrincipal::class.java)
                            startActivity(intent)

                        }
                    }
                    if(!fetched){
                        Toast.makeText(applicationContext,"No se encontró el curso",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("CodigoClases", "Failed to read value.", error.toException())
                }

            })



        }
    }
}