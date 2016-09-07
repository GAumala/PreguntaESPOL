package espol.ihm.preguntaespol

import android.view.View
import android.widget.TextView

/**
 * Created by gesuwall on 9/3/16.
 */

class PreguntaDetailHolder(view: View): ScoreItemHolder(view, null) {
    val answerBtn: View
    val countTextView: TextView

    init {

        answerBtn = view.findViewById(R.id.respuestas_new)
        countTextView = view.findViewById(R.id.respuestas_title) as TextView
        answerBtn.setOnClickListener({
            val ctx = view.context as PreguntaDetailActivity
            ctx.answer()
        })
    }

    fun updateRespTitle(count: Int){
        val texto = if (count == 1) "Respuesta" else "Respuestas"
        countTextView.text = count.toString() + " " + texto
    }

}