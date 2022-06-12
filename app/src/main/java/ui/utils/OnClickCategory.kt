package ui.utils

import model.articles.Article
import model.source.Source


interface OnClickCategory {
    fun onClickCategory(category: Article)

    fun onLongClickCategory(category: Article)
}