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
 * Created by gesuwall on 8/22/16.
 */

class ActivityFeedAdapter(val ctx: Context, val preguntaList: ArrayList<Pregunta>): RecyclerView.Adapter<ScoreItemHolder>() {

    var queryList: ArrayList<Pregunta>? = null
    val mBackground: Int

    val itemList: MutableList<Pregunta>
     get() = queryList ?: preguntaList

    init {
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

        preguntaHolder.setOnClickListener(View.OnClickListener {
            (ctx as PreguntasActivity).showPregunta(pregunta)
        })

        if(pregunta.photoPath != null){
            preguntaHolder.setPhotoPath(pregunta.photoPath!!, false)
        }
    }

    fun addNewPregunta(newPregunta: Pregunta){
        preguntaList.add(0, newPregunta)
    }

    override fun getItemViewType(position: Int) = if(itemList[position].photoPath == null) 0 else 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreItemHolder {
        val holder =  ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                R.layout.pregunta_text, parent, false), mBackground)
        if(viewType == 1) holder.revealPhoto()
        return holder

    }

    fun onNewQuery(query: String){
        Log.d("ActivityFeed", "search $query")
        if(query.isEmpty())
            queryList = null
        else
            queryList = ArrayList(preguntaList.filter { it.titulo.contains(query) || it.contenido.contains(query) })
    }

}