package com.justForPractice.JustForPractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class MyAdapter internal constructor(val context: Context,val itemViewModel: ItemViewModel) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var items = emptyList<Item>()


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view =LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val item = items[position]
        holder.itemView.txvTitle.text = item.Title
        holder.itemView.txvDescription.text = item.Description
        holder.itemView.txvDate.text = item.Data
        holder.itemView.setOnClickListener {
            itemViewModel.delete(item)
            notifyDataSetChanged()

        }

    }
    internal fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

            return items.size

    }
}