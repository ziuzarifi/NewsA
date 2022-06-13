package ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.databinding.FragmentWebCategoryBinding
import com.google.android.material.snackbar.Snackbar
import model.articles.Article
import model.articles.Source
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

        binding.webView.apply {
            // WebViewClient allows you to handle
            // onPageFinished and override Url loading.
            webViewClient = WebViewClient()
            settings.allowFileAccess = true
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)

            val file: File

            var title = arguments?.getString("title")
            val re = "[^A-Za-z0-9 ]".toRegex()

            title = re.replace(title.toString(), "")


            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                file = File(
                    Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS
                    ),
                    "Item$title"
                )

                if (arguments?.getBoolean("isOnline") == true){
                    loadUrl(arguments?.getString("url") ?: "https://newsapi.org/")
                }else{
                    loadUrl("$file.mht")
                }
            }

        }

        binding.fab.setOnClickListener {
            view?.let {

                it1 -> Snackbar.make(it1, "Article saved successfully", Snackbar.LENGTH_LONG).show()

                val file: File

                var title = arguments?.getString("title")
                val re = "[^A-Za-z0-9 ]".toRegex()
                title = re.replace(title.toString(), "")

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    file = File(
                        Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS
                        ),
                        "Item$title"
                    )

//                    Toast.makeText(context, file.toString(), Toast.LENGTH_SHORT).show()

                    binding.webView.saveWebArchive("$file.mht")

                    // viewModel.setAsFavorite(arguments?.getString("title").toString())

                   val currentItem = Article(0, "", "", "", "", Source("", arguments?.getString("source").toString()),
                        arguments?.getString("title").toString(), "", arguments?.getString("urlToImage").toString(),
                        "", true)


                    viewModel.deleteArticleByTitle(arguments?.getString("title").toString())
                    viewModel.upsert(currentItem)

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