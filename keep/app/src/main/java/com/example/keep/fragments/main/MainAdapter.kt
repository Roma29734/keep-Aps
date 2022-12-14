package com.example.keep.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.keep.R
import com.example.keep.data.User
import com.example.keep.databinding.MyRowBinding
import kotlinx.android.synthetic.main.my_row.view.*


//class MyAdapter(val callback: (Int /* some id *?) -> Unit)
class ListAdapter(
    val callback: (id: Int, title: String, subtitle: String, favourites: Boolean) -> Unit
): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()


    class MyViewHolder(val binding: MyRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(MyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.textTitle.text = currentItem.title
        holder.itemView.textSubTitle.text = currentItem.subTitle


        if(currentItem.favourites) {
            holder.itemView.imageStar.setImageResource(R.drawable.ic_star_icon_select)
        } else {
            holder.itemView.imageStar.setImageResource(R.drawable.ic_star_icon)
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.imageStar.setOnClickListener {
          callback(currentItem.id, currentItem.title, currentItem.subTitle, currentItem.favourites)
        }
    }
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}



