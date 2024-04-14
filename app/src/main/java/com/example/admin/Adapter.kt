import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.Booking
import com.example.admin.R


class Adapter(private val context: Context, private val list: ArrayList<Booking>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.booking_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val booking = list[position]
        holder.bind(booking)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ic: TextView = itemView.findViewById(R.id.tvIC)
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val no : TextView = itemView.findViewById(R.id.tvNo)
        private val type: TextView = itemView.findViewById(R.id.tvType)
        private val pName : TextView = itemView.findViewById(R.id.tvPname)

        fun bind(booking: Booking) {
            ic.text = booking.userIc
            name.text = booking.fullName
            no.text = booking.petNo
            type.text = booking.petType
            pName.text = booking.petName

        }
    }
}