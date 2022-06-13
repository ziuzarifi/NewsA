package ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.databinding.FragmentSearchNewsBinding
import model.api.RetrofitInstance
import model.articles.Article
import model.articles.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.adapters.ArticlesAdapter
import ui.utils.OnClickArticle

class SearchNewsFragment : Fragment(), OnClickArticle {

    lateinit var binding: FragmentSearchNewsBinding
    private val adapter = ArticlesAdapter(this)
    private var q: String? = ""
    var articlesCon: List<Article> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchNewsBinding.inflate(layoutInflater)

        searchNews("")



        return binding.root
    }

    private fun searchNews(q: String) {

        val apiInterface = RetrofitInstance.api

        apiInterface.getNews(
            q = q
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
                            adapter.articlesList = articlesCon
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

                Log.e("TAG", "onQueryTextChange: $p0", )
//                adapter.articlesList =
//                    p0?.let { articlesCon.filter {
//                        it.title.startsWith(p0, ignoreCase = true) } }
//                        ?: emptyList()

                if (p0 != "") {
                    searchNews(q = p0.toString())
                }

                adapter.notifyDataSetChanged()
                return false

            }
        })
    }

    override fun onClickArticle(category: Article) {
        TODO("Not yet implemented")
    }

}