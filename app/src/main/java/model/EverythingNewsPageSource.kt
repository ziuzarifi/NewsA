/*
package model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import model.api.NewsAPI
import model.articles.Article
import model.articles.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class EverythingNewsPageSource(
    private val newsService: NewsAPI,
    private val query: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if(query.isEmpty()){
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val pageNumber: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        val response = newsService.getNews(query, pageNumber, pageSize)
        response.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful){
                    val articles = checkNotNull(response.body()).articles
                    val nextKey = if(articles.size < pageSize) null else pageNumber + 1
                    val prevKey = if(pageNumber == 1) null else pageNumber - 1
                    LoadResult.Page(articles, prevKey, nextKey)
                }else{
                    LoadResult.Error(HttpException(response))
                }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ", )
            }
        })

        return 
    }

}*/
