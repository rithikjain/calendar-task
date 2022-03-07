package `in`.rithikjain.growwcalendartask.adapters

import `in`.rithikjain.growwcalendartask.R
import `in`.rithikjain.growwcalendartask.databinding.YearItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class YearAdapter(
    private val years: List<Int>,
    currentYearOffset: Int,
    private val context: Context,
    val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<YearAdapter.ViewHolder>() {

    var selectedItemPos = currentYearOffset
    var lastSelectedItemPos = currentYearOffset

    inner class ViewHolder(private val binding: YearItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                selectedItemPos = adapterPosition
                onItemClick(selectedItemPos + years[0])

                if (lastSelectedItemPos == -1) {
                    lastSelectedItemPos = selectedItemPos
                } else {
                    notifyItemChanged(lastSelectedItemPos)
                    lastSelectedItemPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }

        fun bind(year: Int, position: Int) {
            if (position == selectedItemPos) {
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.item_background_selected)
            } else {
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.item_background_unselected)
            }
            binding.yearTextView.text = year.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = YearItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(years[position], position)
    }

    override fun getItemCount() = years.size
}