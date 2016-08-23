package espol.ihm.preguntaespol

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.rengwuxian.materialedittext.MaterialEditText
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

class AskActivity : AppCompatActivity() {

    lateinit var materiasSpinner: MaterialBetterSpinner
    lateinit var tituloEditText: MaterialEditText
    lateinit var descEditText: MaterialEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrayOf("Física A",
                "Álgebra Lineal", "Estadística", "Cálculo Integral", "Cálculo Diferencial",
                "Estructuras de Datos", "Análisis de Algoritmos", "Análisis de Redes Eléctricas"));
        materiasSpinner = findViewById(R.id.materias_spinner) as MaterialBetterSpinner
        tituloEditText = findViewById(R.id.titulo_edittext) as MaterialEditText
        descEditText = findViewById(R.id.desc_edittext) as MaterialEditText
        materiasSpinner.setAdapter(adapter);

        val toolbar =  findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d("AskActivity", "click");
        when(item?.itemId){
            R.id.action_send -> {
                if(validarPregunta())
                    finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun validarPregunta(): Boolean{
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
        val descStr = descEditText.text
        if(descStr.isEmpty()){
            descEditText.error = "Por favor ingresa una descripción"
            isValid = false
        }

        if(isValid){
            val intent = Intent()
            intent.putExtra(TITLE_KEY, tituloStr.toString())
            intent.putExtra(CONTENT_KEY, descStr.toString())
            intent.putExtra(MATERIA_KEY, materiaStr.toString())
            setResult(Activity.RESULT_OK, intent)
        }

        return isValid
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.editor_actions, menu);
        return true;
    }

    companion object {
        val TITLE_KEY = "AskActivity.title"
        val CONTENT_KEY = "AskActivity.content"
        val MATERIA_KEY = "AskActivity.materia"
    }
}
