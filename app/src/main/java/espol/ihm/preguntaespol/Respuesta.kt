package espol.ihm.preguntaespol

/**
 * Created by gesuwall on 7/28/16.
 */
class Respuesta(var usuario: Usuario, var pregunta: Pregunta,contenido: String,score: Int,date: Long): ScoreItem(contenido, score, date) {
    var link = ""
    init {
        usuario.respuestas.add(this)
    }
}
