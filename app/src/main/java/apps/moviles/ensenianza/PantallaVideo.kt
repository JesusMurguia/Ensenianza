package apps.moviles.ensenianza

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.*
import kotlinx.android.synthetic.main.activity_pantalla_mensajes.gridview
import kotlinx.android.synthetic.main.activity_pantalla_video.*
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import apps.moviles.ensenianza.R
import kotlinx.android.synthetic.main.activity_pantalla_mensaje.view.*


class PantallaVideo : AppCompatActivity() {
    var videoID="SOjorsZUwX4"
    var adapter: tutorialesAdapter? = null
    var tutoriales = ArrayList<Tutorial>()
    var adapterM: ComentariosAdapter? = null
    var mensajes = ArrayList<Mensaje>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_video)
        cargartutoriales();
        cargarMensajes()


        btnComentarios.setOnClickListener(){
            if (comentarios.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardView,  AutoTransition ());
                comentarios.setVisibility(View.VISIBLE);
                iv_Comentarios.setImageResource(R.drawable.flechaladogris);
            } else {
                TransitionManager.beginDelayedTransition(cardView,  AutoTransition ());
                comentarios.setVisibility(View.GONE);
                iv_Comentarios.setImageResource(R.drawable.flechaabajogris);
            }
        }


        webview.getSettings().setJavaScriptEnabled(true)
        webview.getSettings().setPluginState(PluginState.ON)
        webview.loadUrl("https://www.youtube.com/embed/" + videoID + "?autoplay=1&vq=small")
        webview.setWebChromeClient(WebChromeClient())


        adapter=tutorialesAdapter(this, tutoriales)
        gridview.adapter=adapter
        adapterM=ComentariosAdapter(this, mensajes)
        gv_comentarios.adapter=adapterM
    }

    fun cargarMensajes(){
        mensajes.add(Mensaje("El Chapo","Ana Guzman","Que wen video"))
        mensajes.add(Mensaje("el profe","Ana Guzman","mal video"))
    }
    fun cargartutoriales(){
        tutoriales.add(Tutorial("Geografia", "Mi estado", R.drawable.thumbnail5))
        tutoriales.add(Tutorial("Español", "Signos de puntuacion", R.drawable.thumbnail6))
        tutoriales.add(Tutorial("Matematicas", "Integrales", R.drawable.thumbnail5))
        tutoriales.add(Tutorial("Geografia", "Nacionalidades", R.drawable.thumbnail4))
    }
}

class ComentariosAdapter: BaseAdapter {
    var mensajes= ArrayList<Mensaje>()
    var contexto: Context? =null
    constructor(contexto: Context, mensajes: ArrayList<Mensaje>){
        this.contexto=contexto
        this.mensajes=mensajes
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var mensaje=mensajes[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pantalla_mensaje, null)
        vista.tv_mensaje.setText(mensaje.mensaje)
        vista.tv_nombre.setText(mensaje.from)
        vista.setBackgroundResource(R.color.boton)

        return vista
    }

    override fun getItem(p0: Int): Any {
        return mensajes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mensajes.size
    }
}