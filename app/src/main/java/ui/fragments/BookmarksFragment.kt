package ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.FragmentBookmarksBinding
import model.articles.Article
import ui.NewsViewModel
import ui.adapters.ArticlesAdapter
import ui.utils.OnClickCategory


class BookmarksFragment : Fragment(), OnClickCategory {

    lateinit var binding : FragmentBookmarksBinding
    lateinit var viewModel: NewsViewModel
    private val adapter = ArticlesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookmarksBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)


        viewModel.getAllArticles().observe(viewLifecycleOwner, Observer { arguments ->
            adapter.articlesList
        })

        return binding.root
    }

    override fun onClickCategory(category: Article) {
        TODO("Not yet implemented")
    }

}