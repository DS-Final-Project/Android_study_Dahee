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
            binding.sleep = item
            binding.executePendingBindings()
            //보류 중인 바인딩을 즉시 실행하도록 데이터 바인딩을 요청하는 호출
            //뷰 크기 조정 속도를 높일 수 있으므로 항상 호출하는 것이 좋음
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