package espol.ihm.preguntaespol

import java.util.*

/**
 * Created by gesuwall on 7/28/16.
 */

class Pregunta(var titulo: String, var contenido: String, var score: Int, date: Long, materia: String) {
    val respuestas =  ArrayList<Respuesta>()
    var scoreUsuario = 0
    var photoId = 0
    var link = ""
    init {

    }
}
