package se.nishiyamastudios.pia11jan23

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

//Skicka med lite saker i adaptern : () -> Unit = En bit kod skickas med
class ContactsAdapter(val onItemClicked : (Contactperson) -> Unit) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    var contacts = mutableListOf<Contactperson>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        //När man skapar en ritning för rader så förbereder vi för våra fält och initierar dem
        //Detta är ritningen för en rad, vad är raden

        val contactName : TextView
        val contactPhone : TextView

        init {
            contactName = view.findViewById(R.id.contactItemNameTV)
            contactPhone = view.findViewById(R.id.contactItemPhoneTV)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("pia11debug", "SKAPA RAD")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("pia11debug", "RITA RAD " + position.toString())

        holder.contactName.text = contacts[position].contactname
        holder.contactPhone.text = contacts[position].contactphone

        //Vad som händer när vi klickar på en rad (bit kod Unit som vi skickade med)
        holder.itemView.setOnClickListener {
            onItemClicked(contacts[position])
        }

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}