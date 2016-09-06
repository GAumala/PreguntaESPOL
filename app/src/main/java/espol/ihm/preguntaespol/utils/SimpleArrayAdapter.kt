package espol.ihm.preguntaespol.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import espol.ihm.preguntaespol.R

/**
 * Created by gesuwall on 9/5/16.
 */

class SimpleArrayAdapter(val itemArray: Array<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount() = itemArray.size
    var onItemClickListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val itemView = holder!!.itemView
        val textView = itemView.findViewById(R.id.text1) as TextView
        textView.text = itemArray[position]
        itemView.isClickable = true
        itemView.setOnClickListener { onItemClickListener?.invoke(itemArray[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.text_item,
                parent, false)
        val vh =SimpleViewHolder(v);
        return vh;
    }

    class SimpleViewHolder(view: View): RecyclerView.ViewHolder(view){    }

    abstract class OnItemClickListener: (String) -> Unit { }
}