package espol.ihm.preguntaespol

/**
 * Created by Ecotel on 04/09/16.
 */

class Anuncio(val usuario: Usuario, val titulo: String ,val materia: String, contenido: String, score: Int, date: Long): ScoreItem(contenido, score, date) {
    var photoId = 0

    fun setImage(imageId: Int){
        photoId = imageId
    }
}