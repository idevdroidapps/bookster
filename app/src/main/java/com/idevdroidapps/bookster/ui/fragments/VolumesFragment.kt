package com.idevdroidapps.bookster.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.databinding.FragmentVolumesBinding

class VolumesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentVolumesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_volumes, container, false)

        return binding.root
    }
}