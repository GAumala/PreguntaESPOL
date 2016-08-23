package espol.ihm.preguntaespol


import android.support.v7.widget.RecyclerView

/**
 * interface for activities that have an OnScrollListener to listen to scrolling events happening
 * in the fragments they host
 * Created by gabriel on 3/26/16.
 */

interface ScrollableActivity {
    fun getScrollListener() : RecyclerView.OnScrollListener
}
