package ui

import androidx.lifecycle.ViewModel
import model.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}