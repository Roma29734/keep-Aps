package com.example.keep.ui.fragments.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.keep.R
import com.example.keep.data.dao.User
import com.example.keep.databinding.MyRowBinding
import com.example.keep.ui.fragments.main.MainAdapter
import kotlinx.android.synthetic.main.my_row.view.*

class SearchAdapter(
    val callback: (id: Int, title: String, subtitle: String, favourites: Boolean, date: String) -> Unit
) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(val binding: MyRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MyRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.textTitle.text = currentItem.title
        holder.binding.textSubTitle.text = currentItem.subTitle


        if (currentItem.favourites) {
            holder.binding.imageStar.setImageResource(R.drawable.ic_star_icon_select)
        } else {
            holder.binding.imageStar.setImageResource(R.drawable.ic_star_icon)
        }

        holder.binding.rowLayout.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.imageStar.setOnClickListener {
            if (currentItem.favourites) {
                holder.binding.imageStar.setImageResource(R.drawable.ic_star_icon)
            } else {
                holder.binding.imageStar.setImageResource(R.drawable.ic_star_icon_select)
            }
            Log.d("mainAdapter", "обработка клика ${currentItem.id}")
            callback(
                currentItem.id,
                currentItem.title,
                currentItem.subTitle,
                currentItem.favourites,
                currentItem.date,
            )
        }
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}