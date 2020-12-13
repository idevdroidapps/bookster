package com.idevdroidapps.bookster.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.databinding.FragmentSearchBinding
import com.idevdroidapps.bookster.ui.utils.onClickKeyboardDoneButton
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModel

/**
 * Simple [Fragment] class for accepting using search input.
 */
class SearchFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        initViews(binding)
        binding.lifecycleOwner
        return binding.root
    }

    /**
     * Initializes the EditText input
     *
     * @param   binding The [FragmentSearchBinding] received
     */
    private fun initViews(binding: FragmentSearchBinding) {
        val editText = binding.editTextVolumeSearch
        editText.onClickKeyboardDoneButton {
            setCurrentQuery(binding)
        }
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editTextVolumeSearch.hint = ""
            } else {
                binding.editTextVolumeSearch.hint = getString(R.string.hint_search)
            }
        }
        binding.imageViewSearchButton.setOnClickListener {
            setCurrentQuery(binding)
        }
    }

    /**
     * Saves the current query to [SharedViewModel]
     *
     * @param   binding     The [FragmentSearchBinding] received
     */
    private fun setCurrentQuery(binding: FragmentSearchBinding) {
        binding.editTextVolumeSearch.clearFocus()

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

        val query = binding.editTextVolumeSearch.text.toString()
        viewModel.setCurrentQuery(query)

        this.findNavController()
            .navigate(SearchFragmentDirections.actionSearchFragmentToVolumesFragment())
    }

}