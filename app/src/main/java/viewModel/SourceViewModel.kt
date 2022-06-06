package viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import model.api.RetrofitInstance
import model.source.Source
import model.source.SourceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.adapters.SourcesAdapter

class SourceViewModel(
    application: Application,
): AndroidViewModel(application) {

    val getNewsSource = MutableLiveData<List<Source>>()

    fun getNewsBySource() {
        RetrofitInstance.api.getNewsBySource()
            .enqueue(object : Callback<SourceModel> {
                override fun onResponse(call: Call<SourceModel>, response: Response<SourceModel>) {

                    if (response.isSuccessful) {
                        response.body().let {
                            if (it != null) {
                                getNewsSource.postValue(it.sources)
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<SourceModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}", )
                }

            })
    }

}