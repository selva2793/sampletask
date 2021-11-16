package com.repository.views.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.repository.GlideAppImpl
import com.repository.R
import com.repository.Utils
import com.repository.databinding.ItemTrendingRepoBinding
import com.repository.extension.bindDrawableStart
import com.repository.extension.containsIgnoreCase
import com.repository.models.TrendingRepo
import java.lang.Exception

class TrendingAdapter constructor(val utils: Utils, var list: ArrayList<TrendingRepo>) :
    BaseSearchAdapter<TrendingRepo, TrendingAdapter.TrendingViewHolder>(list) {

    override fun initViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding: ItemTrendingRepoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_trending_repo,
            parent,
            false
        )
        return TrendingViewHolder(binding)
    }

    override fun bindViewHolderData(holder: TrendingViewHolder, position: Int, data: TrendingRepo) {
        holder.bindView(data)
    }

    override fun hasSearchKey(value: TrendingRepo, filterKey: String): Boolean {
        return value.author.containsIgnoreCase(filterKey) || value.name.containsIgnoreCase(filterKey) || value.language.containsIgnoreCase(
            filterKey
        ) || value.description.containsIgnoreCase(filterKey)
    }

    inner class TrendingViewHolder(private val binding: ItemTrendingRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(trendingRepo: TrendingRepo) {
            binding.repoName.text = if(trendingRepo.name != null && trendingRepo.name.isNotEmpty()) trendingRepo.name else binding.root.context.resources.getString(R.string.empty_text)
            binding.repoAuthor.text = if(trendingRepo.author != null && trendingRepo.author.isNotEmpty()) trendingRepo.author else binding.root.context.resources.getString(R.string.empty_text)
            binding.repoDesc.text = if(trendingRepo.description != null && trendingRepo.description.isNotEmpty()) trendingRepo.description else binding.root.context.resources.getString(R.string.empty_text)
            binding.repoLanguage.text = if(trendingRepo.language != null && trendingRepo.language.isNotEmpty()) trendingRepo.language else binding.root.context.resources.getString(R.string.empty_text)
            binding.repoStarsCount.text = if(trendingRepo.stars != null) trendingRepo.stars.toString() else binding.root.context.resources.getString(R.string.zero_text)
            try {
                GlideAppImpl.loadImage(
                        binding.root.context,
                        trendingRepo.avatar,
                        binding.repoIcon,
                        drawable(trendingRepo.languageColor)
                )
                binding.repoLanguage.bindDrawableStart(drawable(trendingRepo.languageColor))
            } catch (e: Exception) {
            }
        }

        private fun getColor(colorCode: String): Int {
            return Color.parseColor(colorCode)
        }

        private fun drawable(colorCode: String): Drawable? {
            return utils.getColouredDrawable(getColor(colorCode))
        }
    }
}