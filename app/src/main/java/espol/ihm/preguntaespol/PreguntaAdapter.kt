package espol.ihm.preguntaespol

import android.content.Context
import android.support.v7.widget.RecyclerView
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

class PreguntaAdapter(val ctx: Context): RecyclerView.Adapter<PreguntaAdapter.PreguntaHolder>() {

    val preguntaList: ArrayList<Pregunta>
    val mBackground: Int

    init {
        preguntaList = CrearPreguntas.Companion.completarPreguntas()
        var mTypedValue = TypedValue()
        ctx.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }
    override fun getItemCount() = preguntaList.size

    override fun onBindViewHolder(holder: PreguntaHolder?, position: Int) {
        val pregunta = preguntaList[position]
        val preguntaHolder = holder!!
        preguntaHolder.setTitle(pregunta.titulo)
        preguntaHolder.setSummary(pregunta.contenido)
        preguntaHolder.setDate(pregunta.date)
        preguntaHolder.setMateria(pregunta.materia)



    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PreguntaHolder {
        return PreguntaHolder(LayoutInflater.from(ctx).inflate(
                R.layout.pregunta_text, parent, false), mBackground)
    }

    class PreguntaHolder(view: View, mBackground: Int): RecyclerView.ViewHolder(view) {
        val title: TextView
        val username: TextView
        val text: TextView
        val points: TextView
        val materia: TextView
        val time: TextView
        val upvote: ImageView
        val downvote: ImageView

        init {
            title = view.findViewById(R.id.pregunta_title) as TextView
            username = view.findViewById(R.id.username) as TextView
            text = view.findViewById(R.id.pregunta_summary) as TextView
            points = view.findViewById(R.id.points)  as TextView
            materia = view.findViewById(R.id.materia)  as TextView
            time = view.findViewById(R.id.time)  as TextView

            upvote = view.findViewById(R.id.upvote) as ImageView
            downvote = view.findViewById(R.id.downvote) as ImageView
            view.findViewById(R.id.root).setBackgroundResource(mBackground)
        }

        fun setTitle(titleText: String){
            title.text = titleText
        }

        fun setUsername(usrText: String){
            username.text = usrText
        }

        fun setSummary(summary: String){
            text.text = summary
        }

        fun setPoints(score: Int){
            points.text = "$score"
        }

        fun setDate(dateMilis: Long){
            time.text = Utils.getHoraVerdadera(dateMilis)
        }

        fun setMateria(materiaTxt: String){
            materia.text = "#$materiaTxt"
        }
    }

}