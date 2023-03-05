package com.example.prac_android.step6.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.prac_android.R.drawable
import com.example.prac_android.databinding.ListItemSleepNightBinding
import com.example.prac_android.step6.convertDurationToFormatted
import com.example.prac_android.step6.convertNumericQualityToString
import com.example.prac_android.step6.database.SleepNight
import com.example.prac_android.step6.sleeptracker.SleepNightAdapter.ViewHolder

class SleepNightAdapter : ListAdapter<SleepNight, ViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SleepNight) {
            val res = itemView.context.resources

            binding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
            binding.qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> drawable.ic_sleep_0
                    1 -> drawable.ic_sleep_1
                    2 -> drawable.ic_sleep_2
                    3 -> drawable.ic_sleep_3
                    4 -> drawable.ic_sleep_4
                    5 -> drawable.ic_sleep_5
                    else -> drawable.ic_sleep_active
                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }

}