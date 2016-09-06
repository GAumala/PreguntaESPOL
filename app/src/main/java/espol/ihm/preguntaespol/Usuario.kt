package espol.ihm.preguntaespol

import java.util.*

/**
 * Created by Fernando on 04/09/16.
 */
class Usuario(var nombre: String,var  espol_id: String,var nivel: Int,var titulo: String,var puntos: Int) {
    var preguntas = ArrayList<Pregunta>()
    var respuestas = ArrayList<Respuesta>()
    var anuncios = ArrayList<Anuncio>()
    var recompensas = ArrayList<Recompensa>()

    fun getNombreUsuario(): String{
        return nombre
    }

    companion object {
        val user_default = Usuario("Leonardo Eras", "leras", 5, "Estudiante", 4900)
        fun getActualUser(): Usuario = user_default
    }
}
