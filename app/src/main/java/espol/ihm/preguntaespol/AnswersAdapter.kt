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
        return if(position == 0){
            if(pregunta.photoPath != null) PREGUNTA_FOTO else PREGUNTA
        }
        else if(pregunta.respuestas[position - 1].photoPath != null) RESPUESTA_FOTO else RESPUESTA
    }
    override fun onBindViewHolder(holder: ScoreItemHolder?, position: Int) {
        val preguntaHolder = holder!!
        val scoreItem: ScoreItem
        if( position == 0) { //PREGUNTA
            scoreItem = pregunta
            preguntaHolder.bindScoreItem(pregunta)
            (preguntaHolder as PreguntaDetailHolder).updateRespTitle(itemCount - 1)

        } else {//RESPUESTA
            val resp = pregunta.respuestas[position - 1]
            scoreItem = resp
            preguntaHolder.bindRespuesta(resp)
        }
        if(scoreItem.photoPath != null) preguntaHolder.setPhotoPath(scoreItem.photoPath!!, true)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreItemHolder {
        return when(viewType){
            PREGUNTA, PREGUNTA_FOTO -> {
                val holder = PreguntaDetailHolder(LayoutInflater.from(ctx).inflate(
                    R.layout.pregunta_detail_item, parent, false))
                if(viewType == PREGUNTA_FOTO) holder.revealPhoto()
                holder
            }
            else -> {
                val item = ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                        R.layout.respuesta_item, parent, false), null)
                item.leftFooterText.visibility = View.GONE
                if(viewType == RESPUESTA_FOTO) item.revealPhoto()
                item
            }
        }
    }

    companion object {
        val PREGUNTA = 0
        val PREGUNTA_FOTO = 1
        val RESPUESTA = 2
        val RESPUESTA_FOTO = 3
    }

}