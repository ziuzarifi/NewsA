package ui.adapters

import model.articles.Category
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.CategoryItemBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    var categoryList: List<Category> = emptyList()

    class CategoryHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = CategoryItemBinding.bind(item)

        fun bind(category: Category) = with(binding) {
            tvTitle.text = category.title
            textView.text = category.subtitle
            imView.setImageResource(category.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addCategory(list: List<Category>) {
        categoryList = list
    }


}