package com.jmzd.ghazal.wallpaperappmvvm.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponsePhotos.*
import com.jmzd.ghazal.wallpaperappmvvm.databinding.ItemImagesBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseDiffUtils
import com.jmzd.ghazal.wallpaperappmvvm.utils.loadImage
import javax.inject.Inject

class NewestPhotosAdapter @Inject constructor() : RecyclerView.Adapter<NewestPhotosAdapter.ViewHolder>() {

    private var items = emptyList<ResponsePhotosItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder(private val binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {
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

    fun setData(data: List<ResponsePhotosItem>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}