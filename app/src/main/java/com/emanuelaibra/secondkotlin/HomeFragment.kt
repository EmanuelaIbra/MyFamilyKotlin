package com.emanuelaibra.secondkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMembers: List<Member_Model> = listOf(
            Member_Model(name = "Ela", adress = "9th building,2nd floor,maldiv road", battery = "80%", distance = "210"),
            Member_Model(name = "Ela1",adress = "10th building,2nd floor,maldiv road", battery = "70%", distance = "310"),
            Member_Model(name = "Ela2",adress = "11th building,2nd floor,maldiv road", battery = "90%", distance = "410")
        )
        val adapter=Members(listMembers)
        val recycler =requireView().findViewById<RecyclerView>(R.id.rectangles_member)
        recycler.layoutManager=LinearLayoutManager(requireContext())
        recycler.adapter=adapter
    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

