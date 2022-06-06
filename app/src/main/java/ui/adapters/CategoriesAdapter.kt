package ui.adapters

import model.articles.Categories
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.CategoriesItemBinding
import ui.utils.OnClickCategories

class CategoriesAdapter(

    private val onClick: OnClickCategories
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    var categoriesList: List<Categories> = emptyList()

    class CategoriesHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CategoriesItemBinding.bind(item)

        fun bind(categories: Categories) = with(binding) {
            tvTitle.text = categories.title
            blockNews.setCardBackgroundColor(Color.parseColor(categories.colorId))
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoriesHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val currentItem = categoriesList[position]
        holder.bind(categoriesList[position])
        holder.itemView.setOnClickListener {
            onClick.onClickCategories(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun addCategories(list: List<Categories>) {
        categoriesList = list
    }


}