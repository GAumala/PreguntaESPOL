package espol.ihm.preguntaespol

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.ListFragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.MenuItemCompat.OnActionExpandListener
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils

class MainActivity : AppCompatActivity(), ScrollableActivity, PreguntasActivity {

    lateinit var mDrawerLayout: DrawerLayout
    lateinit var fab: FloatingActionButton
    lateinit var viewPager: ViewPager

    lateinit var fragmentAdapter: MyFragmentAdapter
    private val myScrollListener = object : RecyclerView.OnScrollListener(){
        private var fabIsVisible: Boolean = true
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if(fabIsVisible && dy > 0){
                //Toast.makeText(this@MainTabActivity, "Hide", Toast.LENGTH_SHORT).show()
                val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_out_down);
                fab.startAnimation(anim)
                fabIsVisible = false
            } else if(!fabIsVisible && dy < 0){
                //Toast.makeText(this@MainTabActivity, "Show", Toast.LENGTH_SHORT).show()
                val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_in_up);
                fab.startAnimation(anim)
                fabIsVisible = true
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar =  findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);

        val ab = supportActionBar as ActionBar;
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        viewPager =  findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager, savedInstanceState != null);
        val tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        mDrawerLayout =  findViewById(R.id.drawer_layout) as DrawerLayout

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        setupDrawerContent(mDrawerLayout, navigationView);

        fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, AskActivity::class.java)
            startActivityForResult(intent, REQUEST_ASK);
        }
    }

    private fun setupDrawerContent(mDrawerLayout: DrawerLayout, navigationView: NavigationView) {

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true;
            mDrawerLayout.closeDrawers();
            true;
        };
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        fun onNewQuery(query: String?){
            val fragment = fragmentAdapter.getItem(viewPager.currentItem) as MyListFragment
            fragment.onNewQuery(query)
        }

        menuInflater.inflate(R.menu.sample_actions, menu);
        val searchItem = menu.findItem(R.id.action_search)

        MenuItemCompat.setOnActionExpandListener(searchItem, object : OnActionExpandListener {
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private fun setupViewPager(viewPager: ViewPager, restored: Boolean) {
        fragmentAdapter= MyFragmentAdapter(supportFragmentManager);
        if(!restored) {
            fragmentAdapter.addFragment(MyListFragment.newInstance(MyListFragment.LS_PREGUNTAS_FRAGMENT), "Descubrir");
            fragmentAdapter.addFragment(MyListFragment.newInstance(MyListFragment.LS_MATERIAS_FRAGMENT), "Materias");
        } else {//Si se roto la pantalla los fragments siguen ahi, solo hay que buscarlos
            fragmentAdapter.addFragment(supportFragmentManager.findFragmentByTag(fragmentAdapter.getFragmentTag(R.id.viewpager, 0)), "Descubrir")
            fragmentAdapter.addFragment(supportFragmentManager.findFragmentByTag(fragmentAdapter.getFragmentTag(R.id.viewpager, 1)), "Materias")
        }
        viewPager.adapter = fragmentAdapter;
        viewPager.offscreenPageLimit = 1
    }

    override fun showPregunta(pregunta: Pregunta){
        selectedPregunta = pregunta
        val intent = Intent(this, PreguntaDetailActivity::class.java)
        startActivity(intent)
    }

    override fun getScrollListener(): RecyclerView.OnScrollListener {
        return myScrollListener
    }

    fun insertarNuevaPregunta(data: Intent){
        val titlo = data!!.getStringExtra(AskActivity.TITLE_KEY)
        val desc = data!!.getStringExtra(AskActivity.CONTENT_KEY)
        val materia = data!!.getStringExtra(AskActivity.MATERIA_KEY)
        val newPregunta = Pregunta(titlo, desc, 0, System.currentTimeMillis(), materia)
        val feedFragment = supportFragmentManager.findFragmentByTag(
                fragmentAdapter.getFragmentTag(R.id.viewpager, 0)) as MyListFragment
        (feedFragment.adapter as ActivityFeedAdapter).addNewPregunta(newPregunta)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_ASK){
            insertarNuevaPregunta(data!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        var selectedPregunta: Pregunta? = null
        val REQUEST_ASK = 600
        val REQUEST_ANSWER = 601
    }
}
