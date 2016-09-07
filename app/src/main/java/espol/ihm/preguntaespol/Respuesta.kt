package espol.ihm.preguntaespol

/**
 * Created by gesuwall on 7/28/16.
 */
class Respuesta(var usuario: Usuario,contenido: String,score: Int,date: Long): ScoreItem(contenido, score, date) {
    var photoPath: String? = null
    var link = ""
    init {
        usuario.respuestas.add(this)
    }
}
