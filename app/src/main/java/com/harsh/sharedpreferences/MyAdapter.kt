package com.harsh.sharedpreferences


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(val names : MutableList<String>, private val backgroundColors: MutableList<Int>) : RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.rv_item,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.tv_txtView.text = names[position]
        holder.itemView.setBackgroundColor(backgroundColors[position])
    }

    fun updateData(newNames: List<String>) {
        names.clear()
        names.addAll(newNames)
        notifyDataSetChanged()
    }

    fun updateBackgroundColors(colors: IntArray) {
        backgroundColors.clear()
        backgroundColors.addAll(colors.toList())
        notifyDataSetChanged()
    }
}


class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tv_txtView = itemView.findViewById<TextView>(R.id.tv_txtView)
}