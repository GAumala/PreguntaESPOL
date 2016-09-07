package espol.ihm.preguntaespol

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import espol.ihm.preguntaespol.utils.Utils
import java.util.*
import java.util.zip.Inflater

/**
 * Created by Ecotel on 06/09/16.
 */

class AnunciosAdapter(val ctx: Context): RecyclerView.Adapter<AnuncioHolder>() {

    val anunciosList: ArrayList<Anuncio>
    var queryList: ArrayList<Anuncio>? = null
    val mBackground: Int

    val itemList: MutableList<Anuncio>
        get() = queryList ?: anunciosList

    init {
        anunciosList = CrearPreguntas.Companion.completarAnuncios()
        var mTypedValue = TypedValue()
        ctx.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }
    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: AnuncioHolder?, position: Int) {
        val anuncio = itemList[position]
        val anuncioHolder = holder!!
        ////anuncioHolder.bindAnuncio(anuncio)

        //anuncioHolder.setOnClickListener(View.OnClickListener {
        //    (ctx as PreguntasActivity).showPregunta(anuncio)
        //})
    }

    fun addNewAnuncio(newAnuncio: Anuncio){
        anunciosList.add(0, newAnuncio)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnuncioHolder {
        return AnuncioHolder(LayoutInflater.from(ctx).inflate(
                R.layout.anuncio_item, parent, false), mBackground)
    }

    fun onNewQuery(query: String){
        Log.d("Mis Anuncios", "search $query")
        if(query.isEmpty())
            queryList = null
        else
            queryList = ArrayList(anunciosList.filter { it.titulo.contains(query) || it.contenido.contains(query) })
    }

}
