package com.example.keep.fragments.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.keep.R
import com.example.keep.data.User
import com.example.keep.fragments.main.MainFragmentDirections
import kotlinx.android.synthetic.main.my_row.view.*

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private var userSearch = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userSearch.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userSearch[position]
        holder.itemView.textTitle.text = currentItem.title
        holder.itemView.textSubTitle.text = currentItem.subTitle

        holder.itemView.rowLayout.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<User>){
        this.userSearch = user
        notifyDataSetChanged()
    }
}