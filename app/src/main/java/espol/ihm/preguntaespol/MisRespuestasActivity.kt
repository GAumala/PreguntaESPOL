package espol.ihm.preguntaespol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

/**
 * Created by Ecotel on 06/09/16.
 */


class MisRespuestasActivity : AppCompatActivity(), ScrollableActivity, PreguntasActivity  {
    lateinit var persistFragment: PersistFragment
    private val myScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_detail)

        if(savedInstanceState == null) {
            persistFragment = PersistFragment();
            val fragment = MyListFragment.newInstance(MyListFragment.LS_MIS_RESPUESTAS_FRAGMENT)
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.container, fragment)
            ft.commit()
            setToolbar()
            supportFragmentManager.beginTransaction().add(persistFragment, MisRespuestasActivity.DATA_FRAGMENT).commit()
        }else{
            persistFragment = supportFragmentManager.findFragmentByTag(MisRespuestasActivity.DATA_FRAGMENT) as PersistFragment
            setToolbar()
        }
    }

    fun setToolbar(){
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val view = layoutInflater.inflate(R.layout.score_toolbar_header, null)
        val titleTextView = view.findViewById(R.id.pregunta_title) as TextView
        titleTextView.text = MisRespuestasActivity.TITLE
        toolbar.addView(view)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        fun onNewQuery(query: String?){
            val fragment = supportFragmentManager.findFragmentById(R.id.container) as MyListFragment
            fragment.onNewQuery(query)
        }

        menuInflater.inflate(R.menu.sample_actions, menu);
        val searchItem = menu.findItem(R.id.action_search)

        MenuItemCompat.setOnActionExpandListener(searchItem, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                onNewQuery("")
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean { return true}

        })

        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                onNewQuery(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })

        return true;
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getScrollListener(): RecyclerView.OnScrollListener {
        return myScrollListener
    }

    override fun showPregunta(pregunta: Pregunta){
        MisPreguntasActivity.selectedPregunta = pregunta
        val intent = Intent(this, PreguntaDetailActivity::class.java)
        startActivity(intent)
    }

    fun editarPregunta(data: Intent){
        val titlo = data!!.getStringExtra(MisPreguntasActivity.TITLE_KEY)
        val desc = data!!.getStringExtra(MisPreguntasActivity.CONTENT_KEY)
        val materia = data!!.getStringExtra(MisPreguntasActivity.MATERIA_KEY)
        MisPreguntasActivity.selectedPregunta?.contenido = desc
        MisPreguntasActivity.selectedPregunta?.titulo = titlo
        MisPreguntasActivity.selectedPregunta?.materia = materia

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == MainActivity.REQUEST_EDIT){
            editarPregunta(data!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        var TITLE = "Mis Respuestas"
        val DATA_FRAGMENT = "MisRespuestasActivity.DataFragment"
        val TITLE_KEY = "MisRespuestasActivity.title"
        val MATERIA_KEY = "MisRespuestasActivity.materia"
        val CONTENT_KEY = "MisRespuestasActivity.content"
    }
}