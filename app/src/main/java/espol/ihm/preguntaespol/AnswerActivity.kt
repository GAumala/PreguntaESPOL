package espol.ihm.preguntaespol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.rengwuxian.materialedittext.MaterialEditText

/**
 * Created by gesuwall on 9/3/16.
 */

open class AnswerActivity: AppCompatActivity() {

    lateinit var descEditText: MaterialEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        val toolbar =  findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);
    }

    open fun initLayout(){
        setContentView(R.layout.activity_answer)
        descEditText = findViewById(R.id.desc_edittext) as MaterialEditText
    }

    open fun validarInputs(): Intent? {
        var isValid = true

        val descStr = descEditText.text
        if(descStr.isEmpty()){
            descEditText.error = "Por favor ingresa una descripción"
            isValid = false
        }

        if(isValid){
            val intent = Intent()
            intent.putExtra(CONTENT_KEY, descStr.toString())
            return intent
        }

        return null
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_send -> {
                val intent = validarInputs()
                if(intent != null) {
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.editor_actions, menu);
        return true;
    }

    companion object {
        val CONTENT_KEY = "AskActivity.content"
    }
}