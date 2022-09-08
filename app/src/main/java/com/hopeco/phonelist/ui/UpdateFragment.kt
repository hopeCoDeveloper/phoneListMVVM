package com.hopeco.phonelist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.hopeco.phonelist.R
import com.hopeco.phonelist.data.model.Person
import com.hopeco.phonelist.databinding.FragmentUpdateBinding
import com.hopeco.phonelist.viewmodel.PersonViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class UpdateFragment : Fragment() {
    private val viewModel: PersonViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding: FragmentUpdateBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        //get arguments
        val name = arguments?.getString("name")
        val family = arguments?.getString("family")
        val phoneNum = arguments?.getString("phoneNumber")
        val address = arguments?.getString("address")
        val id = arguments?.getInt("id") ?: 0

        //set value to et
        binding.etName.setText(name)
        binding.etFamily.setText(family)
        binding.etPhoneNumber.setText(phoneNum)
        binding.etAddress.setText(address)

        //update button
        binding.btnUpdate.setOnClickListener()
        {
            val etName = binding.etName.text.toString()
            val etFamily = binding.etFamily.text.toString()
            val etPhone = binding.etPhoneNumber.text.toString()
            val etAddress = binding.etAddress.text.toString()
            if(checkValid(etName,etFamily,etPhone,etAddress))
            {
                val person = Person(id,etName,etFamily,etPhone,etAddress)
                viewModel.updatePerson(person)
                Snackbar.make(binding.root,getString(R.string.success_dialogue),
                    BaseTransientBottomBar.LENGTH_LONG
                ).setBackgroundTint(resources.getColor(R.color.dark_blue)).show()
                findNavController().popBackStack()
                findNavController().navigate(R.id.listFragment)

            }
            else
            {
                Snackbar.make(binding.root,getString(R.string.error_dialogue),
                    BaseTransientBottomBar.LENGTH_LONG
                ).setBackgroundTint(resources.getColor(R.color.dark_blue)).show()
            }
        }
        return binding.root
    }

    private fun checkValid(name:String, family:String, phone:String, address:String) : Boolean
    {
        if(name.isEmpty() || family.isEmpty() || phone.isEmpty() || address.isEmpty())
        {
            return false
        }
        if(!phone.isDigitsOnly() || phone.length != 11)
        {
            return false
        }
        return true
    }


}