package espol.ihm.preguntaespol

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView

class PreguntaDetailActivity : AppCompatActivity() {

    lateinit var selectedPregunta: Pregunta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_detail)


        if(savedInstanceState == null){
            if(MainActivity.selectedPregunta == null)
                finish();
            else{
                selectedPregunta = MainActivity.selectedPregunta!!
                val fragment = MyListFragment.newInstance(MyListFragment.PREGUNTA_FRAGMENT)
                val ft = supportFragmentManager.beginTransaction()
                ft.add(R.id.container, fragment)
                ft.commit()
            }

        }

        setToolbar()
    }

    fun setToolbar(){
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val view = layoutInflater.inflate(R.layout.score_toolbar_header, null)
        val titleTextView = view.findViewById(R.id.pregunta_title) as TextView
        titleTextView.text = selectedPregunta.titulo
        toolbar.addView(view)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

}
