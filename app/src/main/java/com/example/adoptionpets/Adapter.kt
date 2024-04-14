import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptionpets.Pets
import com.example.adoptionpets.R
import com.bumptech.glide.Glide


class Adapter(private val context: Context, private val list: ArrayList<Pets>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pet = list[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val no: TextView = itemView.findViewById(R.id.tvNo)
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val age: TextView = itemView.findViewById(R.id.tvAge)
        private val type: TextView = itemView.findViewById(R.id.tvType)
        private val breed: TextView = itemView.findViewById(R.id.tvBreed)
        private val imageView: ImageView = itemView.findViewById(R.id.imgPets)

        fun bind(pet: Pets) {
            no.text = pet.petsNo
            name.text = pet.petsName
            age.text = pet.petsAge
            type.text = pet.petsType
            breed.text = pet.petsBreed

            // Load image from URL using Glide
            Glide.with(itemView.context)
                .load(pet.imageUrl) // Use the imageUrl field from the Pets object
                .placeholder(R.drawable.catwave) // Placeholder image
                .error(R.drawable.dogwave)
                .into(imageView)
        }
    }
}