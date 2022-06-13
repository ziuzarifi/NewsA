package ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentSourcesBinding
import model.api.RetrofitInstance
import model.source.Source
import model.source.SourceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.adapters.SourcesAdapter
import ui.utils.OnClickSources


class SourcesFragment : Fragment(), OnClickSources {

    private val adapter = SourcesAdapter(this)
    lateinit var binding: FragmentSourcesBinding
    var sourcesCon: List<Source> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSourcesBinding.inflate(layoutInflater)

        getSources()

        return binding.root
    }

    private fun getSources() {

        val apiInterface = RetrofitInstance.api


        apiInterface.getNewsBySource()
            .enqueue(object : Callback<SourceModel> {
                override fun onResponse(call: Call<SourceModel>, response: Response<SourceModel>) {

                    if (response.isSuccessful) {


                        binding.apply {
                            pBar.stopNestedScroll()
                            pBar.visibility = View.GONE
                            rcView.layoutManager = GridLayoutManager(context, 2) //requireContext()
                            rcView.adapter = adapter
                            response.body()?.let {
                                sourcesCon = it.sources
                                adapter.addSources(sourcesCon)
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<SourceModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}")
                    binding.pBar.visibility = View.GONE
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

                adapter.sourcesList =
                    p0?.let { sourcesCon.filter { it.name.startsWith(p0, ignoreCase = true) } }
                        ?: emptyList()
                adapter.notifyDataSetChanged()
                return false

            }
        })
    }

    override fun onClickSources(category: Source) {
        val bundle = Bundle()
        bundle.putString("category", category.url)
        findNavController().navigate(R.id.webCategoryFragment, bundle)
    }

}











