package espol.ihm.preguntaespol

/**
 * Created by Ecotel on 04/09/16.
 */

class Anuncio(val usuario: Usuario, val titulo: String ,contenido: String, score: Int, date: Long): ScoreItem(contenido, score, date) {
    var photoId = 0
}