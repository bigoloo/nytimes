package com.bigoloo.nytimes.home.ui.adaptor

import androidx.recyclerview.widget.DiffUtil
import com.bigoloo.nytimes.home.models.Story

class DiffCallback : DiffUtil.ItemCallback<Story>() {

    //2
    override fun areItemsTheSame(oldItem: Story, newItem: Story) =
        oldItem.title == newItem.title

    //3
    override fun areContentsTheSame(oldItem: Story, newItem: Story) =
        oldItem == newItem
}
