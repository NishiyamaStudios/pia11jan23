package se.nishiyamastudios.pia11jan23

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsFragment : Fragment() {

    lateinit var contactsadapter : ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Denna kod skickas in i adaptern, det blir vad som händer när man klickar på en rad.
        //clickedcontact -> är en omdöpning av generiska it, man kan sätta vilket namn man vill
        contactsadapter = ContactsAdapter { clickedcontact ->
            Log.i("pia11debug", "KlICK PÅ RAD")

            //requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, ContactDetailFragment()).addToBackStack(null).commit()

            var godetail = ContactDetailFragment()
            godetail.currentcontact = clickedcontact

            requireActivity().supportFragmentManager.commit {
                add(R.id.fragmentContainerView, godetail)
                addToBackStack(null)
            }
        }

        //Här är Viewn färdigskapad

        val contactRecview = view.findViewById<RecyclerView>(R.id.contactsRV)

        contactRecview.adapter = contactsadapter
        contactRecview.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.addContactButton).setOnClickListener {

            val contactName = view.findViewById<EditText>(R.id.addNameET)
            val contactPhone = view.findViewById<EditText>(R.id.addPhoneET)

            if(contactName.text.toString() == "" || contactPhone.text.toString() == "") {
                //FEL, tomt värde
                Toast.makeText(requireContext(), "Fyll i allt!", Toast.LENGTH_LONG).show()
            } else {

                val tempContact = Contactperson()
                tempContact.contactname = contactName.text.toString()
                tempContact.contactphone = contactPhone.text.toString()

                contactsadapter.contacts.add(tempContact)

                //Måste använda setText här istället för = "", p.g.a den nu är en editable.
                contactName.setText("")
                contactPhone.setText("")

                contactsadapter.notifyDataSetChanged()

            }

        }

    }

}