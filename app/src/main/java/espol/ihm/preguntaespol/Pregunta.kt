package espol.ihm.preguntaespol

import java.util.*

/**
 * Created by gesuwall on 7/28/16.
 */

class Pregunta(var titulo: String, contenido: String, score: Int, date: Long,
               var materia: String): ScoreItem(contenido, score, date) {
    val respuestas =  ArrayList<Respuesta>()
    var photoId = 0
    var link = ""
    init {

    }
}
