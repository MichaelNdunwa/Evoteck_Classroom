package com.evoteckgeospatialconsult.features.deeplink.ui.screen

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentTermsWebViewBinding
import kotlinx.coroutines.delay


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
        val swipeRefreshLayout = binding.swipeRefreshLayout
        val progressOverlay = binding.progressOverlay.progressOverlay

//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_30)
        toolbar.setNavigationIconTint(resources.getColor(R.color.black))
        toolbar.setNavigationOnClickListener {
//            findNavController().navigate(R.id.action_termsWebViewFragment_to_signupFragment)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        progressOverlay.visibility = View.VISIBLE
        swipeRefreshLayout.setColorSchemeResources(R.color.blue)
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.swipe_refresh_background)

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressOverlay.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressOverlay.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                progressOverlay.visibility = View.GONE
                showErrorPage()
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }
        }
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(getString(R.string.terms_url))

        swipeRefreshLayout.setOnRefreshListener {
            webview.reload()
            progressOverlay.visibility = View.VISIBLE
            swipeRefreshLayout.postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 10L)
        }
    }

    private fun showErrorPage() {
        val errorPage = """
            <html>
                <head><title>Oops!</title></head>
                <body style="text-align: center;">
                    <h1>Sorry, something went wrfong.</h1>
                    <p>Please check your internet connection or try again.</p>
                </body>
            </html>
        """.trimIndent()
        binding.webview.loadData(errorPage, "text/html", "UTF-8")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}