package com.jmzd.ghazal.wallpaperappmvvm.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponsePhotos.ResponsePhotosItem
import com.jmzd.ghazal.wallpaperappmvvm.databinding.ItemImagesCategoriesBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.loadImage
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() : PagingDataAdapter<ResponsePhotosItem,
        CategoriesAdapter.ViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        val binding = ItemImagesCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        //جلوگیری از روی هم افتادن دیتای آداپتر موقع اسکرول
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder(private val binding: ItemImagesCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponsePhotosItem) {
            binding.apply {
                //Image
                item.urls?.regular?.let {
                    image.loadImage(it)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ResponsePhotosItem>() {
            override fun areItemsTheSame(oldItem: ResponsePhotosItem, newItem: ResponsePhotosItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResponsePhotosItem, newItem: ResponsePhotosItem): Boolean {
                return oldItem == newItem

            }
        }
    }
}