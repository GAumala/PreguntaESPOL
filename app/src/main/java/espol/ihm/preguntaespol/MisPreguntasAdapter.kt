package espol.ihm.preguntaespol

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by Ecotel on 06/09/16.
 */
open class MisPreguntasAdapter(val ctx: Context): RecyclerView.Adapter<ScoreItemHolder>() {

    /*    init {
            preguntaList = CrearPreguntas.Companion.completarMisPreguntas()
        }

        fun deletePregunta(pregunta: Pregunta){
            preguntaList.remove(pregunta)
        }*/
var preguntaList: ArrayList<Pregunta>
var queryList: ArrayList<Pregunta>? = null
val mBackground: Int

val itemList: MutableList<Pregunta>
    get() = queryList ?: preguntaList

init {
    preguntaList = CrearPreguntas.Companion.completarMisPreguntas()
    var mTypedValue = TypedValue()
    ctx.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
    mBackground = mTypedValue.resourceId;
}
override fun getItemCount() = itemList.size

override fun onBindViewHolder(holder: ScoreItemHolder?, position: Int) {
    val pregunta = itemList[position]
    val preguntaHolder = holder!!
    preguntaHolder.bindPregunta(pregunta)
    preguntaHolder.bindScoreItem(pregunta)
    preguntaHolder.bindEdit(pregunta, this, ctx)
    preguntaHolder.setOnClickListener(View.OnClickListener {
        (ctx as PreguntasActivity).showPregunta(pregunta)
    })

}

    fun deletePregunta(pregunta: Pregunta){
        preguntaList.remove(pregunta)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreItemHolder {
        return ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                R.layout.pregunta_edit_item, parent, false), mBackground)
    }

fun onNewQuery(query: String){
    Log.d("ActivityFeed", "search $query")
    if(query.isEmpty())
        queryList = null
    else
        queryList = ArrayList(preguntaList.filter { it.titulo.contains(query) || it.contenido.contains(query) })
}


}