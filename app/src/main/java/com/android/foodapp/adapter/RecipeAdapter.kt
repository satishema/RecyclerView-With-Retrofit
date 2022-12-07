package com.android.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.android.foodapp.listener.OnClickRowListener
import com.android.foodapp.databinding.CustomRowListViewBinding
import com.android.foodapp.model.FoodResponse

class RecipeAdapter(
    private val listener: OnClickRowListener
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    private var recipeList = ArrayList<FoodResponse>()
    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(recipeList : List<FoodResponse>){
        this.recipeList = recipeList as ArrayList<FoodResponse>
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