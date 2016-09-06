package espol.ihm.preguntaespol.materias

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import espol.ihm.preguntaespol.MyListFragment
import espol.ihm.preguntaespol.R
import espol.ihm.preguntaespol.ScrollableActivity

/**
 * Created by gesuwall on 9/5/16.
 */

class MateriaFilterActivity: AppCompatActivity(), ScrollableActivity {

    override fun getScrollListener(): RecyclerView.OnScrollListener {
        return object: RecyclerView.OnScrollListener() {}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_container)
        val filter = intent.getStringExtra(MATERIA_FILTER)
        val fragment = MyListFragment.newInstance(MyListFragment.LS_PREGUNTAS_FRAGMENT, filter)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()


        supportActionBar?.title = filter

    }

    companion object {
        val MATERIA_FILTER = "MateriaFilterActivity.filter"
    }
}