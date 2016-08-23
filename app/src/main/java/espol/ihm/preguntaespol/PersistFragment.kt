package espol.ihm.preguntaespol

import android.os.Bundle
import android.support.v4.app.Fragment
import java.util.*

/**
 * Created by gesuwall on 8/23/16.
 */

class PersistFragment: Fragment() {
    var list: ArrayList<Pregunta>? = null
    var selectedPregunta: Pregunta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}