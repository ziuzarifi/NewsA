package ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentBookmarksBinding
import model.articles.Article
import ui.NewsViewModel
import ui.adapters.ArticlesAdapter
import ui.utils.OnClickCategory


class BookmarksFragment : Fragment(), OnClickCategory {

    lateinit var binding : FragmentBookmarksBinding
    lateinit var viewModel: NewsViewModel
    private val adapter = ArticlesAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookmarksBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        binding.rcView.layoutManager = GridLayoutManager(context, 1)
        binding.rcView.adapter = adapter


        viewModel.getAllFavorites().observe(viewLifecycleOwner, Observer {
            adapter.setArticles(it.reversed())
            adapter.notifyDataSetChanged()
        })



        return binding.root
    }

    override fun onClickCategory(category: Article) {
        val bundle = Bundle()

        bundle.putBoolean("isOnline", false)
        bundle.putString("title2", category.title)



        Toast.makeText(context, category.title, Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.webCategoryFragment, bundle)
    }

    override fun onLongClickCategory(category: Article) {
        viewModel.deleteArticleByTitle(category.title)
    }

}