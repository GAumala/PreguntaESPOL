package espol.ihm.preguntaespol

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class PreguntaDetailActivity : AppCompatActivity() {

    lateinit var selectedPregunta: Pregunta
    lateinit var persistFragment: PersistFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_detail)


        if(savedInstanceState == null) {
            if (MainActivity.selectedPregunta == null)
                finish();
            else {
                persistFragment = PersistFragment();
                selectedPregunta = MainActivity.selectedPregunta!!
                val fragment = MyListFragment.newInstance(MyListFragment.PREGUNTA_FRAGMENT)
                val ft = supportFragmentManager.beginTransaction()
                ft.add(R.id.container, fragment)
                ft.commit()
                setToolbar()
                supportFragmentManager.beginTransaction().add(persistFragment, DATA_FRAGMENT).commit()
            }
        }else{
            persistFragment = supportFragmentManager.findFragmentByTag(DATA_FRAGMENT) as PersistFragment
            selectedPregunta = persistFragment.selectedPregunta!!
            setToolbar()
        }

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

    fun answer(){
        val intent = Intent(this, AnswerActivity::class.java)
        startActivityForResult(intent, MainActivity.REQUEST_ANSWER)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_share -> {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, "Puedes responder esta pregunta? -> \n" +
                "${selectedPregunta.titulo} http://www.preguntaespol.ec/q/${System.currentTimeMillis()}")
                startActivity(Intent.createChooser(share, "Compartir con..."))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_actions, menu);
        return super.onCreateOptionsMenu(menu)
    }

    fun insertarNuevaRespuesta(data: Intent){
        val desc = data!!.getStringExtra(AskActivity.CONTENT_KEY)
        val photo = data!!.getStringExtra(AskActivity.PHOTO_KEY)
        val newRes = Respuesta(Usuario.getActualUser(), selectedPregunta, desc, 0, System.currentTimeMillis())
        newRes.photoPath = photo
        selectedPregunta.respuestas.add(newRes)
        val listFragment = supportFragmentManager.findFragmentById(R.id.container) as MyListFragment
        listFragment.adapter?.notifyItemInserted(selectedPregunta.respuestas.size - 1)
        listFragment.adapter?.notifyItemChanged(0)
        
        var user = Usuario.getActualUser()
        user.respuestas.add(newRes)
        user.preguntas_respuestas.add(selectedPregunta)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == MainActivity.REQUEST_ANSWER){
            insertarNuevaRespuesta(data!!)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        persistFragment.selectedPregunta = selectedPregunta
    }

    companion object {
        val DATA_FRAGMENT = "PreguntaDetailActivity.DataFragment"
    }

}
