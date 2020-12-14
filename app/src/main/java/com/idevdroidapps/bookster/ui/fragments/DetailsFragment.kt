package com.idevdroidapps.bookster.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.databinding.FragmentDetailsBinding
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModel

class DetailsFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    @SuppressLint("SetJavaScriptEnabled")
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

        binding.browserButton.setOnClickListener {
            handleButtonClick(binding)
        }

        binding.viewModel = viewModel
        return binding.root
    }

    /**
     * Handles button clicks to open book in web reader
     *
     * @param   binding The [FragmentDetailsBinding] received
     */
    private fun handleButtonClick(binding: FragmentDetailsBinding) {
        binding.browserButton.visibility = View.GONE
        binding.webView.visibility = View.VISIBLE
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = BooksWebViewClient()
        CookieManager.getInstance().setAcceptThirdPartyCookies(binding.webView, true)
        viewModel.currentVolume.value?.let {
            binding.webView.loadUrl(it.accessInfo.webReaderLink)
        }
    }

    /**
     * A private [WebViewClient] class to disable external browser loading
     */
    private class BooksWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return false
        }
    }

}