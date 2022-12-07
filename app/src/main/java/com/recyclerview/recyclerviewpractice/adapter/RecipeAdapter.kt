package com.recyclerview.recyclerviewpractice.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.recyclerview.recyclerviewpractice.OnClickRowListener
import com.recyclerview.recyclerviewpractice.databinding.CustomRowListViewBinding
import com.recyclerview.recyclerviewpractice.model.ApiResponse
import com.recyclerview.recyclerviewpractice.viewModel.RecipeViewModel

class RecipeAdapter(
    private val listener: OnClickRowListener
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    private var recipeList = ArrayList<ApiResponse>()
    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(recipeList : List<ApiResponse>){
        this.recipeList = recipeList as ArrayList<ApiResponse>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CustomRowListViewBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CustomRowListViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        val item = recipeList[position]
        holder.binding.tvRvName.text = item.name
        holder.binding.tvRvCalories.text = item.calories
        holder.binding.tvRvHeadline.text = item.headline
//        Picasso.get().load(item.thumb).into(holder.imageView)
        Glide.with(holder.itemView)
            .load(item.thumb)
            .into(holder.binding.IVImage)

        holder.itemView.setOnClickListener {
            listener.onClickItem(position, item)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}