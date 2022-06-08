package model.repository

import androidx.lifecycle.LiveData
import model.articles.Article
import model.db.ArticleDatabase

class NewsRepository(
    private val db: ArticleDatabase
) {

    suspend fun upsert(article: Article): Long {
        return db.getArticleDao().upsert(article = article)
    }

//    fun getAllArticles(category: String): LiveData<List<Article>> {
//        return db.getArticleDao().getAllArticles(category = category)
//    }


    suspend fun deleteArticle(article: Article) {
        return db.getArticleDao().deleteArticle(article = article)
    }

}