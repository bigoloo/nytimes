package com.bigoloo.nytimes.home.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bigoloo.nytimes.home.R
import com.bigoloo.nytimes.home.databinding.ItemStoryBinding
import com.bigoloo.nytimes.home.models.Story

class StoryAdaptor(diffCallback: DiffUtil.ItemCallback<Story>,private val onClickListener:(story:Story)->Unit) :
    ListAdapter<Story, StoryAdaptor.StoryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(
            ItemStoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position),onClickListener)
    }


    class StoryViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story, onClickListener: (story: Story) -> Unit) {
            binding.root.setOnClickListener {
                onClickListener(story)
            }
            binding.itemStoryHeadline.text = story.title
            story.multimedia?.first()?.let {
                binding.itemStoryThumbnail.isVisible = true
                binding.itemStoryThumbnail.load(it.url) {
                    crossfade(true)
                    transformations(
                        RoundedCornersTransformation(16f)
                    )
                    placeholder(R.color.gray)
                }
            }

        }
    }

}