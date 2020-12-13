package com.idevdroidapps.bookster.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.databinding.FragmentDetailsBinding
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModel

class DetailsFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.closeButton.setOnClickListener {
            this.findNavController().popBackStack()
        }

        binding.viewModel = viewModel
        return binding.root
    }
}