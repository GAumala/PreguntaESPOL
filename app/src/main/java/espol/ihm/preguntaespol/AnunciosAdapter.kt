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

open class AnunciosAdapter(val ctx: Context):  RecyclerView.Adapter<ScoreItemHolder>() {

    val anunciosList: ArrayList<ScoreItem>
    var queryList: ArrayList<ScoreItem>? = null
    val mBackground: Int

    val itemList: MutableList<ScoreItem>
        get() = queryList ?: anunciosList

    init {
        anunciosList = CrearPreguntas.Companion.completarAnuncios()
        var mTypedValue = TypedValue()
        ctx.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }
    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ScoreItemHolder?, position: Int) {
        val anuncio = itemList[position]
        val preguntaHolder = holder!!
        preguntaHolder.bindAnuncio(anuncio)
        preguntaHolder.bindEditAnuncio(anuncio, this, ctx)

    }

    fun deleteAnuncio(anuncio: ScoreItem){
        anunciosList.remove(anuncio)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreItemHolder {
        return ScoreItemHolder(LayoutInflater.from(ctx).inflate(
                R.layout.anuncio_item, parent, false), mBackground)
    }

}
