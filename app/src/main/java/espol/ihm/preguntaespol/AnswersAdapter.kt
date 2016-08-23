package espol.ihm.preguntaespol

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by gesuwall on 8/23/16.
 */

class AnswersAdapter(val ctx: Context, val pregunta: Pregunta): RecyclerView.Adapter<ScoreItemHolder>() {

    override fun getItemCount() = pregunta.respuestas.size + 1

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) PREGUNTA
        else RESPUESTA
    }
    override fun onBindViewHolder(holder: ScoreItemHolder?, position: Int) {
        val preguntaHolder = holder!!
        if( position == 0) { //PREGUNTA
            preguntaHolder.bindScoreItem(pregunta)
        } else //RESUPESTA
            preguntaHolder.bindScoreItem(pregunta.respuestas[position - 1])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreItemHolder {
        return when(viewType){
            PREGUNTA -> {
                ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                    R.layout.pregunta_detail_item, parent, false), null)
            }
            else -> {
                val item = ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                        R.layout.respuesta_item, parent, false), null)
                item.leftFooterText.visibility = View.GONE
                item
            }
        }
    }

    companion object {
        val PREGUNTA = 0
        val RESPUESTA = 1
    }

}