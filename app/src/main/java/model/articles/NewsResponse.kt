package model.articles

import model.articles.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)