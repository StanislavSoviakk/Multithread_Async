package com.example.multithread_async

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.multithread_async.databinding.ListItemBinding

class MainAdapter : ListAdapter<Int, MainAdapter.ViewHolder>(NumberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class NumberDiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldNumber: Int, newNumber: Int): Boolean {
            return oldNumber == newNumber
        }

        override fun areContentsTheSame(oldNumber: Int, newNumber: Int): Boolean {
            return oldNumber == newNumber
        }
    }

    inner class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val numberView: TextView = binding.textNumber

        fun bind(number: Int) {
            numberView.text = number.toString()
        }
    }
}