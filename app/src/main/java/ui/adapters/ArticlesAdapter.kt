package ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.CategoryItemBinding
import model.articles.Article
import ui.utils.OnClickCategory

class ArticlesAdapter(
    private val onClick: OnClickCategory
): RecyclerView.Adapter<ArticlesAdapter.ArticleHolder>() {


    var articlesList = emptyList<Article>()

    class ArticleHolder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = CategoryItemBinding.bind(item)


        fun bind(article: Article) = with(binding) {
            tvTitle.text = article.title
            textView.text = article.source.name
            article.urlToImage?.let {
                pBar.visibility = View.GONE
                Glide.with(imView).load(article.urlToImage).into(imView)
            } ?: {
                pBar.visibility = View.GONE
                imView.visibility = View.GONE
                frame.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val currentItem: Article = articlesList[position]
        holder.bind(articlesList[position])

        holder.itemView.setOnClickListener {
            onClick.onClickCategory(currentItem)
        }
    }


    override fun getItemCount(): Int {
        return articlesList.size
    }

    fun setArticles(list: List<Article>) {
        articlesList = list
    }

}