package com.emanuelaibra.secondkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InviteAdapter(private val listMembers: List<ContactsModel>) :RecyclerView.Adapter<InviteAdapter.ViewHolder>() {

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int):InviteAdapter.ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val item=inflater.inflate(R.layout.invite,parent,false)
        return ViewHolder(item)
    }

    override  fun onBindViewHolder( holder:InviteAdapter.ViewHolder,position: Int) {
        val item=listMembers[position]
        holder.name.text=item.name
    }

    override fun getItemCount(): Int {
        return listMembers.size
    }
    class ViewHolder(private val item: View):RecyclerView.ViewHolder(item){
        val name=item.findViewById<TextView>(R.id.name)
    }
}