package espol.ihm.preguntaespol

import android.view.View

/**
 * Created by gesuwall on 9/3/16.
 */

class PreguntaDetailHolder(view: View): ScoreItemHolder(view, null) {
    val answerBtn: View

    init {
        answerBtn = view.findViewById(R.id.respuestas_new)
        answerBtn.setOnClickListener({
            val ctx = view.context as PreguntaDetailActivity
            ctx.answer()
        })
    }
}