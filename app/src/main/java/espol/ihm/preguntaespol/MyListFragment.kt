/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package espol.ihm.preguntaespol

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import espol.ihm.preguntaespol.utils.DividerItemDecoration
import espol.ihm.preguntaespol.utils.SimpleArrayAdapter
import java.util.*


class MyListFragment : Fragment() {

    var adapter: Adapter<RecyclerView.ViewHolder>? = null
    companion object {
        val FRAGMENT_TYPE = "MyListFragment.fragmentType"
        val PREGUNTA_FILTER = "MyListFragment.filter"
        val LS_PREGUNTAS_FRAGMENT = 1
        val LS_MATERIAS_FRAGMENT = 2
        val PREGUNTA_FRAGMENT = 3
        val LS_ANUNCIOS_FRAGMENT = 4
        val LS_MIS_PREGUNTAS_FRAGMENT = 5
        val LS_MIS_RESPUESTAS_FRAGMENT = 6
        fun newInstance(fragmentType: Int): MyListFragment{
            val newInstanceFrag = MyListFragment()
            val newBundle = Bundle()
            newBundle.putInt(FRAGMENT_TYPE, fragmentType)
            newInstanceFrag.arguments = newBundle
            return newInstanceFrag
        }
        fun newInstance(fragmentType: Int, filter: String): MyListFragment{
            val newInstanceFrag = MyListFragment()
            val newBundle = Bundle()
            newBundle.putInt(FRAGMENT_TYPE, fragmentType)
            newBundle.putString(PREGUNTA_FILTER, filter)
            newInstanceFrag.arguments = newBundle
            return newInstanceFrag
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rv = inflater!!.inflate(R.layout.list, container, false) as RecyclerView
        setupRecyclerView(rv)
        return rv
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        when(arguments.getInt(FRAGMENT_TYPE, 0)){
            LS_PREGUNTAS_FRAGMENT -> {
                var preguntas = CrearPreguntas.Companion.completarPreguntas()

                val filter = arguments.getString(PREGUNTA_FILTER)
                if(filter != null) preguntas = ArrayList(preguntas.filter { it.materia == filter })

                adapter = ActivityFeedAdapter(activity, preguntas) as Adapter<RecyclerView.ViewHolder>
                recyclerView.adapter = adapter!!
                val scrollableActivity = activity as ScrollableActivity
                recyclerView.addOnScrollListener(scrollableActivity.getScrollListener())
            }
            PREGUNTA_FRAGMENT -> {
                adapter = AnswersAdapter(activity,(activity as PreguntaDetailActivity).
                        selectedPregunta) as Adapter<RecyclerView.ViewHolder>
                recyclerView.adapter = adapter!!
            }
            LS_MATERIAS_FRAGMENT -> {
                val mAdapter = SimpleArrayAdapter(resources.getStringArray(R.array.materias_array))
                mAdapter.onItemClickListener = object: SimpleArrayAdapter.OnItemClickListener() {
                    override fun invoke(p1: String) {
                        (activity as MainActivity).mostrarMateria(p1)
                    }
                }
                adapter = mAdapter
                recyclerView.adapter = adapter!!
                recyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

                val scrollableActivity = activity as ScrollableActivity
                recyclerView.addOnScrollListener(scrollableActivity.getScrollListener())
            }
            LS_MIS_PREGUNTAS_FRAGMENT -> {
                adapter = MisPreguntasAdapter(activity) as Adapter<RecyclerView.ViewHolder>
                recyclerView.adapter = adapter!!
                val scrollableActivity = activity as ScrollableActivity
                recyclerView.addOnScrollListener(scrollableActivity.getScrollListener())
            }
            LS_MIS_RESPUESTAS_FRAGMENT -> {
                adapter = MisRespuestasAdapter(activity) as Adapter<RecyclerView.ViewHolder>
                recyclerView.adapter = adapter!!
                val scrollableActivity = activity as ScrollableActivity
                recyclerView.addOnScrollListener(scrollableActivity.getScrollListener())
            }
            LS_ANUNCIOS_FRAGMENT -> {
                adapter = AnunciosAdapter(activity) as Adapter<RecyclerView.ViewHolder>
                recyclerView.adapter = adapter!!
                val scrollableActivity = activity as ScrollableActivity
                recyclerView.addOnScrollListener(scrollableActivity.getScrollListener())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        adapter?.notifyDataSetChanged()
    }

    fun onNewQuery(query: String?){
        if(query != null)
            when(arguments.getInt(FRAGMENT_TYPE, 0)){
                LS_PREGUNTAS_FRAGMENT -> {
                    val feedAdapter = adapter as? ActivityFeedAdapter
                    feedAdapter?.onNewQuery(query)
                    feedAdapter?.notifyDataSetChanged()
                }
            }
    }

}
