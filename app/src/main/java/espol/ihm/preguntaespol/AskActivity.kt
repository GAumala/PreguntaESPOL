package espol.ihm.preguntaespol

import android.content.Intent
import android.widget.ArrayAdapter
import com.rengwuxian.materialedittext.MaterialEditText
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

class AskActivity : AnswerActivity() {

    lateinit var materiasSpinner: MaterialBetterSpinner
    lateinit var tituloEditText: MaterialEditText

    override open fun initLayout(){
        setContentView(R.layout.activity_ask)
        val res = resources
        val materias = res.getStringArray(R.array.materias_array)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, materias);
        materiasSpinner = findViewById(R.id.materias_spinner) as MaterialBetterSpinner
        tituloEditText = findViewById(R.id.titulo_edittext) as MaterialEditText
        descEditText = findViewById(R.id.desc_edittext) as MaterialEditText
        materiasSpinner.setAdapter(adapter);
    }
    override fun validarInputs(): Intent?{
        var isValid = true
        val materiaStr = materiasSpinner.text
        if(materiaStr.isEmpty()) {
            materiasSpinner.error = "Por favor selecciona una materia"
            isValid = false
        }
        val tituloStr = tituloEditText.text
        if(tituloStr.isEmpty()){
            tituloEditText.error = "Por favor ingresa un título"
            isValid = false
        } else if(tituloStr.length > 50){
            tituloEditText.error = "El título no puede ser más de 50 caracteres."
            isValid = false
        }

        if(isValid){
            val intent = super.validarInputs()
            if(intent != null) {
                intent.putExtra(TITLE_KEY, tituloStr.toString())
                intent.putExtra(MATERIA_KEY, materiaStr.toString())
                return intent
            }
        }

        return null
    }


    companion object {
        val TITLE_KEY = "AskActivity.title"
        val MATERIA_KEY = "AskActivity.materia"
        val CONTENT_KEY = AnswerActivity.CONTENT_KEY
        val PHOTO_KEY = AnswerActivity.PHOTO_KEY
    }
}
