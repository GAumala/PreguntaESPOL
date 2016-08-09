package espol.ihm.preguntaespol

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    lateinit var mDrawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar =  findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);

        val ab = supportActionBar as ActionBar;
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        val viewPager =  findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager);
        val tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        mDrawerLayout =  findViewById(R.id.drawer_layout) as DrawerLayout

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        setupDrawerContent(mDrawerLayout, navigationView);

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, AskActivity::class.java)
            startActivity(intent);
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
        menuInflater.inflate(R.menu.sample_actions, menu);
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
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MyFragmentAdapter(supportFragmentManager);
        adapter.addFragment(MyListFragment(), "Descubrir");
        adapter.addFragment(MyListFragment(), "Materias");
        viewPager.adapter = adapter;
    }
}
