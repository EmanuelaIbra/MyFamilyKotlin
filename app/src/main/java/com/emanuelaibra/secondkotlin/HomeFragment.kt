package com.emanuelaibra.secondkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
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


        val inviteAdapter=InviteAdapter(fetchContacts())
        val inviterecycler =requireView().findViewById<RecyclerView>(R.id.recycle_invite)
        inviterecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        inviterecycler.adapter=inviteAdapter
    }

    @SuppressLint("Range")
    private fun fetchContacts() : ArrayList<ContactsModel> {
        val cr=requireActivity().contentResolver
        val cursor=cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)

        val listContacts:ArrayList<ContactsModel> = ArrayList()

        if(cursor!=null && cursor.count>0){
            while(cursor!=null && cursor.moveToNext()){
                val id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasphonenumber=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hasphonenumber>0){
                    val pCur=cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                    arrayOf(id),
                    ""
                    )

                    if (pCur!= null && pCur.count>0){
                        while (pCur!=null && pCur.moveToNext()){
                            val phoneNum=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            listContacts.add(ContactsModel(name, phoneNum))
                        }
                        pCur.close()
                    }
                }
            }
            if (cursor!=null){
                cursor.close()
            }
        }
        return listContacts
    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

