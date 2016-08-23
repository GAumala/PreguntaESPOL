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
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import java.util.ArrayList
import java.util.Random

import espol.ihm.preguntaespol.R

class MyListFragment : Fragment() {

    companion object {
        val FRAGMENT_TYPE = "MyListFragment.fragmentType"

        val LS_PREGUNTAS_FRAGMENT = 1
        val LS_MATERIAS_FRAGMENT = 2
        val PREGUNTA_FRAGMENT = 3
        fun newInstance(fragmentType: Int): MyListFragment{
            val newInstanceFrag = MyListFragment()
            val newBundle = Bundle()
            newBundle.putInt(FRAGMENT_TYPE, fragmentType)
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
                recyclerView.adapter = ActivityFeedAdapter(activity)
                val scrollableActivity = activity as ScrollableActivity
                recyclerView.addOnScrollListener(scrollableActivity.getScrollListener())
            }
            PREGUNTA_FRAGMENT -> {
                recyclerView.adapter = AnswersAdapter(activity,
                        (activity as PreguntaDetailActivity).selectedPregunta)
            }
        }
    }

}
