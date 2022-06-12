package ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.articles.Article
import model.db.ArticleDatabase

@OptIn(DelicateCoroutinesApi::class)
class NewsViewModel(
//    private val newsRepository: NewsRepository,
    application: Application
) : AndroidViewModel(application) {


    private val articleDao = ArticleDatabase(context = application).getArticleDao()

    fun getAllArticles(): List<Article> {
        return articleDao.getAllArticles()
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

    fun setAsFavorite(title: String) = GlobalScope.launch {
        articleDao.setAsFavorite(title)
    }

    fun getAllFavorites(): LiveData<List<Article>> {
        return articleDao.getAllFavorites()
    }

    fun deleteArticleByTitle(title: String) = GlobalScope.launch {
        articleDao.deleteArticleByTitle(title)
    }

}