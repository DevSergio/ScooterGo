package net.scootergo.app.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import net.scootergo.app.R
import net.scootergo.app.model.Scooter

class HomeAdapter(private val scooters: List<Scooter>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        var tvName: TextView? = null
        var tvUnlockCost: TextView? = null
        var tvMinuteCost: TextView? = null

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvUnlockCost = itemView.findViewById(R.id.tvUnlockPrice)
            tvMinuteCost = itemView.findViewById(R.id.tvPrice)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName?.text = scooters[position].name
        holder.tvUnlockCost?.text = scooters[position].unlockCost.toString() + "грн"
        holder.tvMinuteCost?.text = scooters[position].minuteCost.toString() + "грн/мин"
        Glide.with(holder.itemView.context).load(scooters[position].icon).apply(RequestOptions().circleCrop()).into(holder.ivIcon)
    }

    override fun getItemCount() = scooters.size
}