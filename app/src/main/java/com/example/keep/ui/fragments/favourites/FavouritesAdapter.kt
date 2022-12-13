package com.example.keep.ui.fragments.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.keep.R
import com.example.keep.data.dao.User
import com.example.keep.databinding.MyRowBinding
import com.example.keep.ui.fragments.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.my_row.view.*


class FavouritesAdapter(
    val callback: (id: Int, title: String, subtitle: String, favourites: Boolean, date: String) -> Unit
) : RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>() {

    private var favouritesList = emptyList<User>()

    inner class MyViewHolder(val binding: MyRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MyRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currencyFavourites = favouritesList[position]
        holder.binding.textTitle.text = currencyFavourites.title
        holder.binding.textSubTitle.text = currencyFavourites.subTitle
        holder.binding.textView.text = currencyFavourites.date

        if (currencyFavourites.favourites) {
            holder.binding.imageStar.setImageResource(R.drawable.ic_star_icon_select)
        } else {
            holder.binding.imageStar.setImageResource(R.drawable.ic_star_icon)
        }

        holder.binding.rowLayout.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToUpdateFragment(currencyFavourites)

            holder.itemView.findNavController().navigate(action)
        }

        holder.binding.imageStar.setOnClickListener {
            callback(
                currencyFavourites.id,
                currencyFavourites.title,
                currencyFavourites.subTitle,
                currencyFavourites.favourites,
                currencyFavourites.date,
            )
        }
    }

    override fun getItemCount(): Int {
        return favouritesList.size
    }

    fun setData(user: List<User>) {
        this.favouritesList = user
        notifyDataSetChanged()
    }
}