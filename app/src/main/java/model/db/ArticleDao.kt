package model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import model.articles.Article
import model.articles.Category


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles where category = :category")
    fun getAllArticles(category: String): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("delete from articles where category = :category")
    fun deleteByCategory(category: String)
}