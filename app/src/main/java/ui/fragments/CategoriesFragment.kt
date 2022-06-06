package ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.databinding.FragmentCategoriesBinding
import com.example.news.databinding.FragmentCategoryBinding
import model.articles.Article
import ui.adapters.CategoriesAdapter
import model.articles.Categories
import model.source.Source
import ui.adapters.ArticlesAdapter
import ui.utils.OnClickCategories


class CategoriesFragment : Fragment(), OnClickCategories {

    private val adapter = CategoriesAdapter(this)
    lateinit var binding: FragmentCategoriesBinding
    var articlesCon: List<Article> = emptyList()
    private var categories = listOf(
        Categories("General", "#006E58"),
        Categories("Business", "#C9FF8B"),
        Categories("Science", "#FF5722"),
        Categories("Technology", "#FF5A4E"),
        Categories("Health", "#FFEB3B"),
        Categories("Entertainment", "#03A9F4"),
        Categories("Sports", "#673AB7"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        /*val s = "asdaseda sd dsf sdf asd"
        val x = s.contains("dsf")
        print(x)*/
        init()
        return binding.root

    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(context, 2) //requireContext()
            rcView.adapter = adapter
            adapter.addCategories(categories)
        }

        /*binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(p0: String?): Boolean {

                adapterArticles.articlesList =
                    p0?.let { articlesCon.filter { it.title.startsWith(p0, ignoreCase = true) } }
                        ?: emptyList()
                adapterArticles.notifyDataSetChanged()
                return false

            }
        })*/
    }

    override fun onClickCategories(categories: Categories) {
        val bundle = Bundle()
        bundle.putString("category", categories.title)
        findNavController().navigate(com.example.news.R.id.categoryFragment, bundle)
    }

}













