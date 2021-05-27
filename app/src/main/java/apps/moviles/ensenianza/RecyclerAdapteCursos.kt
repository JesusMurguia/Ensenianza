package apps.moviles.ensenianza

import Dominio.Curso
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterCursos (val cursos: ArrayList<Curso>, val listener:View.OnClickListener) : View.OnClickListener,
    RecyclerView.Adapter<RecyclerAdapterCursos.ViewHolderCurso>() {


    override fun onClick(p0: View?) {
        if(listener!==null){
            listener.onClick(p0);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCursos.ViewHolderCurso {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerviewaulas, parent, false);
        view.setOnClickListener(this);
        return ViewHolderCurso(view);
    }

    class ViewHolderCurso(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val nombreClase: TextView = itemView.findViewById(R.id.prin_nombre_aula);
        val icono: ImageView = itemView.findViewById(R.id.prin_icono);
    }



    override fun getItemCount() = cursos.size;
    override fun onBindViewHolder(holder: ViewHolderCurso, position: Int) {
        holder.nombreClase.text = cursos[position].nombre;
        holder.icono.setImageResource(cursos[position].icono);
    }


}