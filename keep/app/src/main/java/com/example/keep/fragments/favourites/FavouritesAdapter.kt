package com.example.keep.fragments.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.keep.R
import com.example.keep.data.User
import com.example.keep.databinding.MyRowBinding
import com.example.keep.fragments.main.MainFragmentDirections
import kotlinx.android.synthetic.main.my_row.view.*


class FavouritesAdapter(
    val callback: (id: Int, title: String, subtitle: String, favourites: Boolean) -> Unit
): RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>() {

    private var favouritesList = emptyList<User>()

    inner class MyViewHolder(val binding: MyRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currencyFavourites = favouritesList[position]
        holder.itemView.textTitle.text = currencyFavourites.title
        holder.itemView.textSubTitle.text = currencyFavourites.subTitle

        if(currencyFavourites.favourites) {
            holder.itemView.imageStar.setImageResource(R.drawable.ic_star_icon_select)
        } else {
            holder.itemView.imageStar.setImageResource(R.drawable.ic_star_icon)
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = FavouritesFragmentDirections
                .actionFavouritesFragmentToUpdateFragment(currencyFavourites)

            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.imageStar.setOnClickListener {
            callback(currencyFavourites.id, currencyFavourites.title, currencyFavourites.subTitle, currencyFavourites.favourites)
        }
    }

    override fun getItemCount(): Int {
        return favouritesList.size
    }

    fun setData(user: List<User>){
        this.favouritesList = user
        notifyDataSetChanged()
    }
}