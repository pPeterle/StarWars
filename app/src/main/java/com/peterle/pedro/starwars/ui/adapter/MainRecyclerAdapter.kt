package com.peterle.pedro.starwars.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peterle.pedro.presentation.model.FilmView
import com.peterle.pedro.starwars.R
import kotlinx.android.synthetic.main.main_recycler_item.view.*

class MainRecyclerAdapter(private val list: List<FilmView>, private val onClick: (item: FilmView) -> Unit): RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder =
            MyViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_recycler_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val listItem = list.get(position)

        viewHolder.itemView.main_recycler_title.text = listItem.title
        viewHolder.itemView.main_recycler_image.setImageResource(listItem.image)
        viewHolder.itemView.main_recycler_layout.setOnClickListener { onClick.invoke(listItem) }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}