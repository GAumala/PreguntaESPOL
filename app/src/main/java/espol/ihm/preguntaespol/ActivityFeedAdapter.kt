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

class ActivityFeedAdapter(val ctx: Context): RecyclerView.Adapter<ScoreItemHolder>() {

    val preguntaList: ArrayList<Pregunta>
    val mBackground: Int

    init {
        preguntaList = CrearPreguntas.Companion.completarPreguntas()
        var mTypedValue = TypedValue()
        ctx.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }
    override fun getItemCount() = preguntaList.size

    override fun onBindViewHolder(holder: ScoreItemHolder?, position: Int) {
        val pregunta = preguntaList[position]
        val preguntaHolder = holder!!
        preguntaHolder.bindPregunta(pregunta)
        preguntaHolder.bindScoreItem(pregunta)


        preguntaHolder.setOnClickListener(View.OnClickListener {
            (ctx as PreguntasActivity).showPregunta(pregunta)
        })


    }

    fun addNewPregunta(newPregunta: Pregunta){
        preguntaList.add(0, newPregunta)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreItemHolder {
        return ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                R.layout.pregunta_text, parent, false), mBackground)
    }


}