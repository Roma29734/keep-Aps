package com.example.keep.ui.widget

import android.security.keystore.KeyNotYetValidException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keep.data.dao.User
import com.example.keep.databinding.WidgetRowBinding

class KeepAdapter: RecyclerView.Adapter<KeepAdapter.MyHolder>() {
    class MyHolder (val binding: WidgetRowBinding): RecyclerView.ViewHolder(binding.root)

    private var widgetKeep = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return  MyHolder(WidgetRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return widgetKeep.size
    }

    fun setData(list: List<User>) {
        widgetKeep = list
        notifyDataSetChanged()
    }


}