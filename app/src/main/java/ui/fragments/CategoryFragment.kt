package ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentCategoryBinding
import model.api.RetrofitInstance
import model.articles.Article
import model.articles.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.adapters.ArticlesAdapter
import ui.utils.OnClickCategory


class CategoryFragment : Fragment(), OnClickCategory {

    lateinit var binding: FragmentCategoryBinding
    private val adapter = ArticlesAdapter(this)
    lateinit var toolBarTitle: TextView
    private var category: String? = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationIcon(R.drawable.ic_back_button)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        return binding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("category") != null) {
            category = arguments!!.getString("category").toString()
        }

        getNewsByCategory(category = category.toString())
        toolBarTitle = view.findViewById(R.id.toolbar_title)
        toolBarTitle.text = category
    }

    private fun getNewsByCategory(category: String) {

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
                            adapter.addArticles(it.articles)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                binding.shimmer.visibility = View.GONE
            }
        })
    }

    override fun onClickCategory(category: Article) {
        val bundle = Bundle()
        bundle.putString("category", category.url)
        findNavController().navigate(R.id.webCategoryFragment, bundle)
    }

}
