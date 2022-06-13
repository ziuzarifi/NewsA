package ui.utils

import model.articles.Article
import model.articles.Category
import model.source.Source


interface OnClickArticle {
    fun onClickArticle(category: Article)

}