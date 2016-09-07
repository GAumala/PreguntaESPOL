package espol.ihm.preguntaespol

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.TextView

/**
 * Created by Ecotel on 06/09/16.
 */

class MisPreguntasActivity : AppCompatActivity(), ScrollableActivity, PreguntasActivity  {
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
            val fragment = MyListFragment.newInstance(MyListFragment.LS_MIS_PREGUNTAS_FRAGMENT)
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.container, fragment)
            ft.commit()
            setToolbar()
            supportFragmentManager.beginTransaction().add(persistFragment, MisPreguntasActivity.DATA_FRAGMENT).commit()
        }else{
            persistFragment = supportFragmentManager.findFragmentByTag(MisPreguntasActivity.DATA_FRAGMENT) as PersistFragment
            setToolbar()
        }
    }

    fun setToolbar(){
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val view = layoutInflater.inflate(R.layout.score_toolbar_header, null)
        val titleTextView = view.findViewById(R.id.pregunta_title) as TextView
        titleTextView.text = TITLE
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
        selectedPregunta = pregunta
        val intent = Intent(this, PreguntaDetailActivity::class.java)
        startActivity(intent)
    }

    fun editarPregunta(data: Intent){
        val titlo = data!!.getStringExtra(MisPreguntasActivity.TITLE_KEY)
        val desc = data!!.getStringExtra(MisPreguntasActivity.CONTENT_KEY)
        val materia = data!!.getStringExtra(MisPreguntasActivity.MATERIA_KEY)
        selectedPregunta?.contenido = desc
        selectedPregunta?.titulo = titlo
        selectedPregunta?.materia = materia

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == MainActivity.REQUEST_EDIT){
            editarPregunta(data!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        val TITLE = "Mis Preguntas"
        var selectedPregunta: Pregunta? = null
        val DATA_FRAGMENT = "MisPreguntasActivity.DataFragment"
        val TITLE_KEY = "MisPreguntasActivity.title"
        val MATERIA_KEY = "MisPreguntasActivity.materia"
        val CONTENT_KEY = "MisPreguntasActivity.content"
    }

}