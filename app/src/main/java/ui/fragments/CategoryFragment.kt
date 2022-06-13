package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentCategoryBinding
import model.articles.Article
import ui.adapters.CategoryAdapter
import model.articles.Category
import ui.utils.OnClickCategory
import ui.utils.OnClickSearch


class CategoryFragment : Fragment(), OnClickCategory, OnClickSearch {

    private val adapter = CategoryAdapter(this)
    lateinit var binding: FragmentCategoryBinding
    var articlesCon: List<Article> = emptyList()

    private var categories = listOf(
        Category("General", "#006E58"),
        Category("Business", "#C9FF8B"),
        Category("Science", "#FF5722"),
        Category("Technology", "#FF5A4E"),
        Category("Health", "#FFEB3B"),
        Category("Entertainment", "#03A9F4"),
        Category("Sports", "#673AB7"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        binding.searchView.setOnClickListener {
            findNavController().navigate(R.id.searchForNewsFragment)
        }

        init()

        return binding.root

    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(context, 2) //requireContext()
            rcView.adapter = adapter
            adapter.addCategory(categories)
        }
    }

    override fun onClickCategory(category: Category) {
        val bundle = Bundle()
        bundle.putString("category", category.title)
        findNavController().navigate(R.id.categoryFragment, bundle)
    }

    override fun onClickSearch(category: Article) {
        val bundle = Bundle()
        bundle.putString("search", category.q)
        findNavController().navigate(R.id.searchForNewsFragment, bundle)
    }


}













