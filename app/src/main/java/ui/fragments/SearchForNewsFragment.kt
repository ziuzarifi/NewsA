package ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentCategoryBinding
import com.example.news.databinding.FragmentSearchForNewsBinding
import com.example.news.databinding.FragmentSourcesBinding
import model.api.RetrofitInstance
import model.articles.Article
import model.articles.NewsResponse
import model.source.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.adapters.ArticlesAdapter
import ui.adapters.SearchForNewsAdapter
import ui.utils.OnClickSearch

class SearchForNewsFragment : Fragment(), OnClickSearch {

    lateinit var binding: FragmentSearchForNewsBinding
    private val adapter = SearchForNewsAdapter(this)
    private var category: String? = ""
    var articlesCon: List<Article> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchForNewsBinding.inflate(layoutInflater)

        searchNews(category = category.toString())

        return binding.root
    }

    private fun searchNews(category: String) {

        val apiInterface = RetrofitInstance.api

        apiInterface.getNews(
            category = category
        ).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    binding.apply {
                        shimmer.stopShimmerAnimation()
                        shimmer.visibility = View.GONE
                        rcView.layoutManager = GridLayoutManager(context, 1)
                        rcView.adapter = adapter
                        response.body()?.let {
                            articlesCon = it.articles
                            adapter.addArticles(articlesCon)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                binding.shimmer.visibility = View.GONE
            }
        })

        // Realize SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(p0: String?): Boolean {

                adapter.articlesList =
                    p0?.let { articlesCon.filter { it.title.startsWith(p0, ignoreCase = true) } }
                        ?: emptyList()
                adapter.notifyDataSetChanged()
                return false

            }
        })
    }

    override fun onClickSearch(category: Article) {
        val bundle = Bundle()
        bundle.putString("category", category.url)
        findNavController().navigate(R.id.searchForNewsFragment, bundle)
    }

}