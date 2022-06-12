package ui.fragments

import android.R.attr.defaultValue
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.databinding.FragmentWebCategoryBinding
import com.google.android.material.snackbar.Snackbar
import ui.MainActivity
import ui.NewsViewModel
import java.io.File


class WebCategoryFragment : Fragment() {

    lateinit var binding: FragmentWebCategoryBinding
    lateinit var viewModel: NewsViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebCategoryBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)



//        binding.fab.setOnClickListener(
            //            val file: File
//            val fileNameExternal = "myPage"
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//
//                file = File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOCUMENTS),
//                    fileNameExternal
//                )
//
//                binding.webView.saveWebArchive("$file.mht")
//            }
//        )

        binding.webView.apply {
            // WebViewClient allows you to handle
            // onPageFinished and override Url loading.
            webViewClient = WebViewClient()


            settings.allowFileAccess = true



//            read offline
            val file: File
            val fileNameExternal = "myPage"
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

                file = File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS),
                    fileNameExternal
                )

                loadUrl("$file.mht")
            }






            loadUrl(arguments?.getString("category") ?: "https://newsapi.org/")

            settings.javaScriptEnabled = true

            settings.setSupportZoom(true)

            binding.fab.setOnClickListener {
                view?.let {

                        it1 -> Snackbar.make(it1, "Article saved successfully", Snackbar.LENGTH_LONG).show()

                    val file: File
                    val fileNameExternal = "myPage"
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

                        file = File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS),
                            fileNameExternal
                        )

                        binding.webView.saveWebArchive("$file.mht")
                    }



                }
            }
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