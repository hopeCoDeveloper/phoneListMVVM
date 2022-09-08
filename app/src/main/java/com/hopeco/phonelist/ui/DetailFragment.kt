package com.hopeco.phonelist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.hopeco.phonelist.R
import com.hopeco.phonelist.data.model.Person
import com.hopeco.phonelist.databinding.FragmentDetailBinding
import com.hopeco.phonelist.viewmodel.PersonViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {
    private val viewModel: PersonViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        val name = arguments?.getString("name") ?: ""
        val family = arguments?.getString("family") ?: ""
        val phoneNum = arguments?.getString("phoneNumber") ?: ""
        val address = arguments?.getString("address") ?: ""
        val id = arguments?.getInt("id") ?: 0

        binding.txtName.text = name
        binding.txtFamily.text = family
        binding.txtPhoneNumber.text = phoneNum
        binding.txtAddress.text = address

        //set update button
        binding.btnUpdate.setOnClickListener()
        {
            val bundle = Bundle()
            bundle.putString("name",name)
            bundle.putString("family",family)
            bundle.putString("phoneNumber",phoneNum)
            bundle.putString("address",address)
            bundle.putInt("id",id)
            findNavController().popBackStack()
            findNavController().navigate(R.id.updateFragment,bundle)
        }

        //set delete button
        binding.btnDelete.setOnClickListener()
        {
            val person = Person(id,name,family,phoneNum,address)
            viewModel.deletePerson(person)
            Snackbar.make(binding.root,getString(R.string.delete_dialogue),
                BaseTransientBottomBar.LENGTH_LONG
            ).setBackgroundTint(resources.getColor(R.color.dark_blue)).show()
            findNavController().popBackStack()
            findNavController().navigate(R.id.listFragment)
        }

        return binding.root
    }


}