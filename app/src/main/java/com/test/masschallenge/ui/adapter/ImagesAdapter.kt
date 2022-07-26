package com.test.masschallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.test.masschallenge.R
import com.test.masschallenge.databinding.ItemImagesBinding
import com.test.masschallenge.model.response.places.Images
import java.util.*


class ImagesAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Images>() {

        override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem.url == newItem.url
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HolderClass(ItemImagesBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HolderClass -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Images>) {
        differ.submitList(list)
    }

    class HolderClass
    constructor(
        private val binding: ItemImagesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Images) {
            Glide.with(binding.itemImage.context)
                .load(item.url)
                .fitCenter()
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(32)))
                .error(R.drawable.ic_broken_image)
                .into(binding.itemImage)
        }
    }

}

