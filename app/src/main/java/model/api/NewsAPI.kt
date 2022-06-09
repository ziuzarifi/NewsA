package model.api

import androidx.annotation.IntRange
import model.articles.NewsResponse
import model.source.SourceModel
import model.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    fun getNews(
        @Query("country")
        countryCode: String = "us",

        @Query("page") /*@IntRange(from = 1)*/ pageNumber : Int = 1,

        /*@Query("pageSize") @IntRange(from = 1, to = Long.MAX_VALUE) pageSize : Int = DEFAULT_BUFFER_SIZE,*/

        @Query("category") category: String? = "",

        @Query("apiKey")
        apiKey: String = API_KEY,

        @Query("q")
        q: String? = ""
    ): Call<NewsResponse>

 /*   @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>*/

    @GET("/v2/top-headlines/sources")
    fun getNewsBySource(
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("country")
        countryCode: String = "us"
    ): Call<SourceModel>

}