package apps.moviles.ensenianza

import Dominio.Actividad
import Dominio.Clase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.moviles.ensenianza.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pantalla_clase_detalle.*
import kotlinx.android.synthetic.main.activity_pantalla_clase_grabada.*
import java.lang.Exception
import java.lang.reflect.Executable

class PantallaClaseDetalle : AppCompatActivity() {
    lateinit var clase: Clase
    var actividades=ArrayList<Actividad>()
    lateinit var ids:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_clase_detalle);

        var bundle=intent.extras
        if(bundle!=null){
            clase=bundle.get("clase") as Clase
            iconoClaseGeografia.setImageResource(clase.icono)
            textMateriaGeografia.text=clase.nombre
            textMtraGeografia.text=clase.nombreProfesor
            ids= bundle.get("ids") as ArrayList<String>




            var rootRef = FirebaseDatabase.getInstance()
            var cursosfb = rootRef.getReference("actividades")
            cursosfb.addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (i in dataSnapshot.children) {
                        for(id in ids){
                            if(i.key==id){
                                var titulo=i.child("titulo").getValue(String::class.java).toString()
                                var subtitulo=i.child("subtitulo").getValue(String::class.java).toString()
                                var fechaEntrega=i.child("fechaEntrega").getValue(String::class.java).toString()
                                var fechaInicio=i.child("fechaInicio").getValue(String::class.java).toString()
                                var descripcion=i.child("descripcion").getValue(String::class.java).toString()
                                var a=Actividad(titulo,subtitulo,fechaEntrega,fechaInicio,id,descripcion)

                                actividades.add(a)
                            }
                        }
                    }

                    println(actividades.size)
                    if (actividades.size>2){
                        tareaUrgenteBttn.text=actividades.get(0).titulo
                        tareaNormalBttn.text=actividades.get(1).titulo
                        tareaBttn.text=actividades.get(2).titulo
                    }else if(actividades.size>1){
                        tareaUrgenteBttn.text=actividades.get(0).titulo
                        tareaNormalBttn.text=actividades.get(1).titulo
                    }else if(actividades.size>0){
                        tareaUrgenteBttn.text=actividades.get(0).titulo
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("CodigoClases", "Failed to read value.", error.toException())
                }

            })
        }
        tareaUrgenteBttn.setOnClickListener(){
            try {
                var intent= Intent(this, PantallaEntregarAsignacion::class.java)
                intent.putExtra("actividad",actividades[0])
                intent.putExtra("clase",clase)
                startActivity(intent)
            }catch (e:Exception){
                Toast.makeText(this,"No existe esa tarea",Toast.LENGTH_SHORT).show()
            }

        }
        tareaNormalBttn.setOnClickListener(){
            try {
                var intent= Intent(this, PantallaEntregarAsignacion::class.java)
                intent.putExtra("actividad",actividades[1])
                intent.putExtra("clase",clase)
                startActivity(intent)
            }catch (e:Exception){
                Toast.makeText(this,"No existe esa tarea",Toast.LENGTH_SHORT).show()
            }
        }
        tareaBttn.setOnClickListener(){
            try {
                var intent= Intent(this, PantallaEntregarAsignacion::class.java)
                intent.putExtra("actividad",actividades[2])
                intent.putExtra("clase",clase)
                startActivity(intent)
            }catch (e:Exception){
                Toast.makeText(this,"No existe esa tarea",Toast.LENGTH_SHORT).show()
            }
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