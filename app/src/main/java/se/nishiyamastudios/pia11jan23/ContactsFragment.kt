package se.nishiyamastudios.pia11jan23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsFragment : Fragment() {

    var contactsadapter = ContactsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Här är Viewn färdigskapad

        val contactRecview = view.findViewById<RecyclerView>(R.id.contactsRV)

        contactRecview.adapter = contactsadapter
        contactRecview.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.addContactButton).setOnClickListener {

            val contactName = view.findViewById<EditText>(R.id.addNameET).text.toString()
            val contactPhone = view.findViewById<EditText>(R.id.addPhoneET).text.toString()

            val tempContact = Contactperson()
            tempContact.contactname = contactName
            tempContact.contactphone = contactPhone

            contactsadapter.contacts.add(tempContact)

            contactsadapter.notifyDataSetChanged()

        }

    }

}