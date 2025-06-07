package com.roomdbwithjsondata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roomdbwithjsondata.databinding.ItemCountryBinding
import com.roomdbwithjsondata.model.Country

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val countries = mutableListOf<Country>()

    inner class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.binding.textCountryName.text = country.commonName
        holder.binding.textCapital.text = country.cca3
        Glide.with(holder.itemView)
            .load(country.countryFlag)
            .into(holder.binding.imgFlag)
    }

    override fun getItemCount(): Int = countries.size

    fun submitList(newList: List<Country>) {
        val diffCallback = CountryDiffCallback(countries, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        countries.clear()
        countries.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CountryDiffCallback(
    private val oldList: List<Country>,
    private val newList: List<Country>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Assuming Country has a unique identifier, here let's use 'name'
        return oldList[oldItemPosition].cca3 == newList[newItemPosition].cca3
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // You can do a full data class equality check if Country is a data class
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
