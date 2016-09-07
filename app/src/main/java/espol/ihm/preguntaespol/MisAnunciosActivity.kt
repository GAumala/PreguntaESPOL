package espol.ihm.preguntaespol

/**
 * Created by Ecotel on 05/09/16.
 */
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.TextView

class MisAnunciosActivity : AppCompatActivity(), ScrollableActivity   {
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
            val fragment = MyListFragment.newInstance(MyListFragment.LS_ANUNCIOS_FRAGMENT)
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.container, fragment)
            ft.commit()
            setToolbar()
            supportFragmentManager.beginTransaction().add(persistFragment, DATA_FRAGMENT).commit()
        }else{
            persistFragment = supportFragmentManager.findFragmentByTag(DATA_FRAGMENT) as PersistFragment
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

    fun claim(){
        val intent = Intent(this, ClaimActivity::class.java)
        startActivityForResult(intent, MainActivity.REQUEST_ANSWER)
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
    companion object {
        val TITLE = "Mis Anuncios"
        val DATA_FRAGMENT = "MisAnuncios.DataFragment"

    }
}
