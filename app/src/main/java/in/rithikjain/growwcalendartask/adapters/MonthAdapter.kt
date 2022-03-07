package `in`.rithikjain.growwcalendartask.adapters

import `in`.rithikjain.growwcalendartask.R
import `in`.rithikjain.growwcalendartask.databinding.MonthItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MonthAdapter(
    private val months: List<String>,
    currentMonth: Int,
    private val context: Context,
    val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<MonthAdapter.ViewHolder>() {

    var selectedItemPos = currentMonth
    var lastSelectedItemPos = currentMonth

    inner class ViewHolder(private val binding: MonthItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                selectedItemPos = adapterPosition
                onItemClick(selectedItemPos)

                if (lastSelectedItemPos == -1) {
                    lastSelectedItemPos = selectedItemPos
                } else {
                    notifyItemChanged(lastSelectedItemPos)
                    lastSelectedItemPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }

        fun bind(month: String, position: Int) {
            if (position == selectedItemPos) {
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.item_background_selected)
            } else {
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.item_background_unselected)
            }
            binding.monthTextView.text = month
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MonthItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(months[position], position)
    }

    override fun getItemCount() = months.size
}