package com.evoteckgeospatialconsult.features.deeplink.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentTermsWebViewBinding


class TermsWebViewFragment : Fragment() {

    private var _binding: FragmentTermsWebViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermsWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbar
        val webview = binding.webview

//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_30)
        toolbar.setNavigationIconTint(resources.getColor(R.color.black))
        toolbar.setNavigationOnClickListener {
//            findNavController().navigate(R.id.action_termsWebViewFragment_to_signupFragment)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(getString(R.string.terms_url))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}