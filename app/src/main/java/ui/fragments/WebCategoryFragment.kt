package ui.fragments

import android.R.attr.defaultValue
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.news.databinding.FragmentWebCategoryBinding


class WebCategoryFragment : Fragment() {

    lateinit var binding: FragmentWebCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebCategoryBinding.inflate(layoutInflater)

        binding.webView.apply {
            // WebViewClient allows you to handle
            // onPageFinished and override Url loading.
            webViewClient = WebViewClient()

            loadUrl(arguments?.getString("category") ?: "https://newsapi.org/")

            settings.javaScriptEnabled = true

            settings.setSupportZoom(true)
        }

        return binding.root
    }

    // if you press Back button this code will work
    /*override fun onBackPressed() {
        // if your webview can go back it will go back
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }*/
}