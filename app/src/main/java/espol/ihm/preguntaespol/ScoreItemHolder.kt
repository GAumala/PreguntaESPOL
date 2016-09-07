package espol.ihm.preguntaespol

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.criptext.monkeykitui.photoview.PhotoViewActivity
import com.squareup.picasso.Picasso
import espol.ihm.preguntaespol.utils.Utils
import java.io.File

/**
 * Created by gesuwall on 8/23/16.
 */
open class ScoreItemHolder(view: View, mBackground: Int?): RecyclerView.ViewHolder(view) {
        val title: TextView?
        val username: TextView?
        val text: TextView
        val points: TextView?
        val leftFooterText: TextView
        val time: TextView
        val upvote: ImageView?
        val downvote: ImageView?
        val root: View
        val editBtn: Button?
        val deleteBtn: Button?
        val photo: ImageView?

    init {
            title = view.findViewById(R.id.pregunta_title) as TextView?
            username = view.findViewById(R.id.username) as TextView?
            text = view.findViewById(R.id.pregunta_summary) as TextView
            points = view.findViewById(R.id.points)  as TextView?
            leftFooterText = view.findViewById(R.id.left_footer_text)  as TextView
            time = view.findViewById(R.id.time)  as TextView

            upvote = view.findViewById(R.id.upvote) as ImageView?
            downvote = view.findViewById(R.id.downvote) as ImageView?
            root = view.findViewById(R.id.root)
            editBtn = view.findViewById(R.id.image_button_anuncio1) as Button?
            deleteBtn = view.findViewById(R.id.image_button_anuncio2) as Button?
            photo = view.findViewById(R.id.photo_image_view) as ImageView?
            if(mBackground != null)
                root.setBackgroundResource(mBackground)
        }

        fun revealPhoto(){
            photo?.visibility = View.VISIBLE
        }
        fun setUpvoteListener(listener: View.OnClickListener){
            upvote?.setOnClickListener(listener)
        }

        fun setDownvoteListener(listener: View.OnClickListener){
            downvote?.setOnClickListener(listener)
        }
        fun setEditBtnListener(listener: View.OnClickListener){
            editBtn?.setOnClickListener(listener)
        }

        fun setDeleteBtnListener(listener: View.OnClickListener){
            deleteBtn?.setOnClickListener(listener)
        }
        fun setTitle(titleText: String){
            title?.text = titleText
        }

        fun setUsername(usrText: String){
            val usrTextView = username
            if(usrTextView != null)
                usrTextView.text = usrText
            else
                leftFooterText.text = usrText
        }

        fun setSummary(summary: String){
            text.text = summary
        }

        fun setPoints(score: Int){
            points?.text = "$score"
        }

        fun setDate(dateMilis: Long){
            time.text = Utils.getHoraVerdadera(dateMilis)
        }

        fun setLeftFooterText(leftFooterTextTxt: String){
            leftFooterText.text = "#$leftFooterTextTxt"
        }

        fun setPhotoPath(path: String, clickable: Boolean){
            Picasso.with(itemView.context)
                    .load(File(path))
                    .into(photo)
            if(clickable){
                photo!!.setOnClickListener({
                    val intent = Intent(photo.context, PhotoViewActivity::class.java)
                    intent.putExtra(PhotoViewActivity.IMAGE_DATA_PATH, path)
                    photo.context?.startActivity(intent)
                })
            }
        }

        fun setVote(userVote: Int){
            if(userVote == 0){
                upvote?.setImageDrawable(ContextCompat.getDrawable(upvote.context, R.drawable.ic_thumb_up))
                downvote?.setImageDrawable(ContextCompat.getDrawable(upvote?.context, R.drawable.ic_thumb_down))
                points?.setTextColor(ContextCompat.getColor(points.context, R.color.neutral))
            } else if(userVote == 1){
                upvote?.setImageDrawable(ContextCompat.getDrawable(upvote.context, R.drawable.ic_thumb_up_pressed))
                downvote?.setImageDrawable(ContextCompat.getDrawable(upvote?.context, R.drawable.ic_thumb_down))
                points?.setTextColor(ContextCompat.getColor(points.context, R.color.upvote))
            } else {
                upvote?.setImageDrawable(ContextCompat.getDrawable(upvote.context, R.drawable.ic_thumb_up))
                downvote?.setImageDrawable(ContextCompat.getDrawable(upvote?.context, R.drawable.ic_thumb_down_pressed))
                points?.setTextColor(ContextCompat.getColor(points.context, R.color.downvote))
            }
        }

        fun setOnClickListener(listener: View.OnClickListener){
            root.setOnClickListener(listener)
        }

        fun bindPregunta(pregunta: Pregunta){
            setTitle(pregunta.titulo)
            setLeftFooterText(pregunta.materia)
        }

        fun bindScoreItem(item: ScoreItem){
            setSummary(item.contenido)
            setDate(item.date)
            setPoints(item.score)
            setVote(item.scoreUsuario)
            setUpvoteListener(View.OnClickListener {
                if(item.scoreUsuario != 1){
                    item.score += 1 - item.scoreUsuario
                    item.scoreUsuario = 1
                } else {
                    item.score -= 1
                    item.scoreUsuario = 0
                }
                setVote(item.scoreUsuario)
                setPoints(item.score)
            })

            setDownvoteListener(View.OnClickListener {
                if(item.scoreUsuario != -1){
                    item.score -= 1 + item.scoreUsuario
                    item.scoreUsuario = -1
                } else {
                    item.score += 1
                    item.scoreUsuario = 0
                }
                setVote(item.scoreUsuario)
                setPoints(item.score)
            })

        }
        fun bindEdit(pregunta: Pregunta, adapter: MisPreguntasAdapter, ctx: Context){
            setEditBtnListener(View.OnClickListener {
                val intent = Intent(ctx , AskActivity::class.java)
                intent.putExtra(MisPreguntasActivity.TITLE_KEY, title?.text.toString())
                intent.putExtra(MisPreguntasActivity.MATERIA_KEY, leftFooterText?.text.toString())
                intent.putExtra(MisPreguntasActivity.CONTENT_KEY, text?.text.toString())
                //startActivityForResult(intent, MainActivity.REQUEST_EDIT);
            })

            setDeleteBtnListener(View.OnClickListener {
                Usuario.getActualUser().preguntas.remove(pregunta)
                adapter.deletePregunta(pregunta as Pregunta)
                adapter.notifyItemRemoved(adapterPosition)
            })
        }
        fun bindRespuesta(item: Respuesta){
            setUsername(item.usuario.getNombreUsuario())
            bindScoreItem(item)
        }

        fun bindAnuncio(item: ScoreItem){
            item as Anuncio
            setUsername(item.usuario.getNombreUsuario())
            setSummary(item.contenido)
            setDate(item.date)
            setTitle(item.titulo)
            setLeftFooterText(item.materia)
        }

        fun bindEditAnuncio(anuncio: ScoreItem, adapter: AnunciosAdapter, ctx: Context){
            setEditBtnListener(View.OnClickListener {
                val intent = Intent(ctx , AskActivity::class.java)
                intent.putExtra(MisPreguntasActivity.TITLE_KEY, title?.text.toString())
                intent.putExtra(MisPreguntasActivity.MATERIA_KEY, leftFooterText?.text.toString())
                intent.putExtra(MisPreguntasActivity.CONTENT_KEY, text?.text.toString())
                //startActivityForResult(intent, MainActivity.REQUEST_EDIT);
            })

            setDeleteBtnListener(View.OnClickListener {
                Usuario.getActualUser().anuncios.remove(anuncio)
                adapter.deleteAnuncio(anuncio as Anuncio)
                adapter.notifyItemRemoved(adapterPosition)
            })
        }
    }

