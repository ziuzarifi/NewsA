package ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.articles.Article
import model.db.ArticleDao
import model.db.ArticleDatabase

@OptIn(DelicateCoroutinesApi::class)
class NewsViewModel(
//    private val newsRepository: NewsRepository,
    application: Application
) : AndroidViewModel(application) {


    private val articleDao = ArticleDatabase(context = application).getArticleDao()

    fun getAllArticles(category: String): LiveData<List<Article>> {
        return articleDao.getAllArticles(category = category)
    }

    fun upsert(article: Article) = GlobalScope.launch {
        articleDao.upsert(article = article)

    }
//
    fun deleteArticle(article: Article) = GlobalScope.launch {
        articleDao.deleteArticle(article = article)
    }


    fun deleteAllArticles(category: String) = GlobalScope.launch {
        articleDao.deleteByCategory(category = category)
    }

}