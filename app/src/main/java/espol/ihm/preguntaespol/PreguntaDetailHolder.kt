package espol.ihm.preguntaespol

import android.view.View
import android.widget.TextView

/**
 * Created by gesuwall on 9/3/16.
 */

class PreguntaDetailHolder(view: View, answersCount: Int): ScoreItemHolder(view, null) {
    val answerBtn: View
    val countTextView: TextView

    init {

        answerBtn = view.findViewById(R.id.respuestas_new)
        countTextView = view.findViewById(R.id.respuestas_title) as TextView
        val texto = if (answersCount == 1) "Respuesta" else "Respuestas"
        countTextView.text = answersCount.toString() + " " + texto
        answerBtn.setOnClickListener({
            val ctx = view.context as PreguntaDetailActivity
            ctx.answer()
        })
    }

}