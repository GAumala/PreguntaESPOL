package espol.ihm.preguntaespol

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by gesuwall on 7/28/16.
 */

class MyFragmentAdapter(fn: FragmentManager) : FragmentPagerAdapter(fn) {
    private val mFragments  = ArrayList<Fragment>();
    private val mFragmentTitles = ArrayList<String>();


    fun addFragment(fragment: Fragment, title: String) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position];
    }

    override fun getCount(): Int {
        return mFragments.count()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitles[position];
    }

    fun getFragmentTag(viewPagerId: Int, fragmentPosition: Int): String
    {
        return "android:switcher:$viewPagerId:$fragmentPosition";
    }
}
