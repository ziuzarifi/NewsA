package ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.SourcesItemBinding
import model.source.Source
import ui.utils.OnClickSources

class SourcesAdapter(
    private val onClick: OnClickSources
): RecyclerView.Adapter<SourcesAdapter.SourcesHolder>() {
    var sourcesList: List<Source> = emptyList()

    class SourcesHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = SourcesItemBinding.bind(item)

        fun bind(sources: Source) = with(binding) {
            tvTitle.text = sources.name
            textView.text = sources.category
            when(sources.category){
                "general" -> {imView.setColorFilter(Color.parseColor("#006E58"))
                imView.setBackgroundColor(Color.parseColor("#006E58"))}
                "business" -> {imView.setColorFilter(Color.parseColor("#C9FF8B"))
                imView.setBackgroundColor(Color.parseColor("#C9FF8B"))}
                "science" -> {imView.setColorFilter(Color.parseColor("#FF5722"))
                imView.setBackgroundColor(Color.parseColor("#FF5722"))}
                "technology" -> {imView.setColorFilter(Color.parseColor("#FF5A4E"))
                imView.setBackgroundColor(Color.parseColor("#FF5A4E"))}
                "health" -> {imView.setColorFilter(Color.parseColor("#FFEB3B"))
                imView.setBackgroundColor(Color.parseColor("#FFEB3B"))}
                "entertainment" -> {imView.setColorFilter(Color.parseColor("#03A9F4"))
                imView.setBackgroundColor(Color.parseColor("#03A9F4"))}
                "sports" -> {imView.setColorFilter(Color.parseColor("#673AB7"))
                imView.setBackgroundColor(Color.parseColor("#673AB7"))}

            }
//            imView.setColorFilter(Color.parseColor(sources.colorId))
//            imView.setBackgroundColor(Color.parseColor(sources.colorId))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sources_item, parent, false)
        return SourcesHolder(view)
    }

    override fun onBindViewHolder(holder: SourcesHolder, position: Int) {
        val currentItem: Source = sourcesList[position]
        holder.bind(sourcesList[position])
        holder.itemView.setOnClickListener {
            onClick.onClickSources(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return sourcesList.size
    }

    fun addSources(list: List<Source>) {
        sourcesList = list
    }


}