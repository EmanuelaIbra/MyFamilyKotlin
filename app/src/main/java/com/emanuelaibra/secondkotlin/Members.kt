package com.emanuelaibra.secondkotlin
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class Members(private val listMembers: List<Member_Model>) :RecyclerView.Adapter<Members.ViewHolder>() {

    override  fun onCreateViewHolder( parent: ViewGroup,viewType: Int):Members.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val item=inflater.inflate(R.layout.member_listview,parent,false)
        return ViewHolder(item)
    }

    override  fun onBindViewHolder( holder:Members.ViewHolder,position: Int) {
        val item=listMembers[position]
        holder.name.text=item.name
        holder.adress.text=item.adress
        holder.battery.text=item.battery
        holder.distance.text=item.distance
    }

    override fun getItemCount(): Int {
        return listMembers.size
     }
    class ViewHolder(private val item:View ):RecyclerView.ViewHolder(item){
        val imageUser=item.findViewById<ImageView>(R.id.img_user)
        val name=item.findViewById<TextView>(R.id.member_name)
        val adress=item.findViewById<TextView>(R.id.member_location)
        val battery=item.findViewById<TextView>(R.id.text_battery)
        val distance=item.findViewById<TextView>(R.id.text_distance)

    }
 }