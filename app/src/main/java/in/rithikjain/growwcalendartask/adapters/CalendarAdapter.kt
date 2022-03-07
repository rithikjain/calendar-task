package `in`.rithikjain.growwcalendartask.adapters

import `in`.rithikjain.growwcalendartask.R
import `in`.rithikjain.growwcalendartask.databinding.CalendarItemBinding
import `in`.rithikjain.growwcalendartask.databinding.MonthItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(
    private val days: List<String>,
    currentDayWithOffset: Int,
    private val context: Context,
    val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    var selectedItemPos = currentDayWithOffset
    var lastSelectedItemPos = currentDayWithOffset

    inner class ViewHolder(private val binding: CalendarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (days[adapterPosition] != "") {
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
        }

        fun bind(day: String, position: Int) {
            if (position == selectedItemPos) {
                binding.dateTextView.background =
                    ContextCompat.getDrawable(context, R.drawable.item_background_selected)
            } else {
                binding.dateTextView.background =
                    ContextCompat.getDrawable(context, R.drawable.item_background_unselected)
            }
            binding.dateTextView.text = day
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CalendarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(days[position], position)
    }

    override fun getItemCount() = days.size
}