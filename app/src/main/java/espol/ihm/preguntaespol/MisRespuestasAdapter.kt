package espol.ihm.preguntaespol

import android.content.Context
import android.util.TypedValue
import java.util.*

/**
 * Created by Ecotel on 06/09/16.
 */

class MisRespuestasAdapter(ctx: Context): MisPreguntasAdapter(ctx) {

    init {
        preguntaList = CrearPreguntas.Companion.completarMisRespuestas()
    }
}
