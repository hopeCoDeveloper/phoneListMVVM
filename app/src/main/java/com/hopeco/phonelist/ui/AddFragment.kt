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
import com.hopeco.phonelist.databinding.FragmentAddBinding
import com.hopeco.phonelist.viewmodel.PersonViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {

    private val viewModel: PersonViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding : FragmentAddBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        binding.btnAdd.setOnClickListener()
        {
            val etName = binding.etName.text.toString()
            val etFamily = binding.etFamily.text.toString()
            val etPhone = binding.etPhoneNumber.text.toString()
            val etAddress = binding.etAddress.text.toString()
            if(checkValid(etName,etFamily,etPhone,etAddress))
            {
                val person = Person(0,etName,etFamily,etPhone,etAddress)
                viewModel.insertPerson(person)
                Snackbar.make(binding.root,getString(R.string.save_dialogue),LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(R.color.dark_blue)).show()
                findNavController().popBackStack()
                findNavController().navigate(R.id.listFragment)
            }
            else
            {
                Snackbar.make(binding.root,R.string.error_dialogue,LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(R.color.dark_blue)).show()
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