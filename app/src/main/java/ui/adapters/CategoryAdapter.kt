package ui.adapters

import model.articles.Category
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.CategoryItemBinding
import ui.utils.OnClickCategory

class CategoryAdapter(

    private val onClick: OnClickCategory
) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private var categoryList: List<Category> = emptyList()

    class CategoryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CategoryItemBinding.bind(item)

        fun bind(category: Category) = with(binding) {
            tvTitle.text = category.title
            blockNews.setCardBackgroundColor(Color.parseColor(category.colorId))
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.bind(categoryList[position])
        holder.itemView.setOnClickListener {
            onClick.onClickCategory(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addCategory(list: List<Category>) {
        categoryList = list
    }


}