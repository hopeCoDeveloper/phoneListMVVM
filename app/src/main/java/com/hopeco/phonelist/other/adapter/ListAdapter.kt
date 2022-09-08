package com.hopeco.phonelist.other.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hopeco.phonelist.R
import com.hopeco.phonelist.data.model.Person
import com.hopeco.phonelist.databinding.RecyclerListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var items: List<Person> = emptyList()

    inner class ListViewHolder(val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            RecyclerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.txtName.text = items[position].name
        holder.binding.txtFamily.text = items[position].family
        holder.binding.txtPhoneNumber.text = items[position].phoneNum
        holder.binding.txtAddress.text = items[position].address

        holder.binding.linearRecycler.setOnClickListener()
        {
            val bundle = Bundle()
            bundle.putString("name",items[position].name)
            bundle.putString("family",items[position].family)
            bundle.putString("phoneNumber",items[position].phoneNum)
            bundle.putString("address",items[position].address)
            bundle.putInt("id",items[position].id)

            it.findNavController().navigate(R.id.action_listFragment_to_detailFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list: List<Person>) {
        items = list
        notifyDataSetChanged()
    }
}