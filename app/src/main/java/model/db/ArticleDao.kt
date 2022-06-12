package model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import model.articles.Article
import model.articles.Category


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Article>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("delete from articles where category = :category")
    fun deleteByCategory(category: String)


    @Query("UPDATE articles SET isFavorite = 1 WHERE title = :title")
    fun setAsFavorite(title: String)

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    fun getAllFavorites(): LiveData<List<Article>>


    @Query("delete from articles where title = :title")
    fun deleteArticleByTitle(title: String)

}