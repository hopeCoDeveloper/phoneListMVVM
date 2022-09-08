package com.hopeco.phonelist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hopeco.phonelist.R
import com.hopeco.phonelist.databinding.FragmentListBinding
import com.hopeco.phonelist.other.adapter.ListAdapter
import com.hopeco.phonelist.viewmodel.PersonViewModel


class ListFragment : Fragment() {

    private val viewModel: PersonViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding : FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater)
        val items = viewModel.getAllPeople()
        val recycler = binding.recyclerList
        val adapter = ListAdapter()

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        items.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        binding.floatingActionButton.setOnClickListener()
        {
            findNavController().navigate(R.id.addFragment)
        }

        return binding.root
    }



}