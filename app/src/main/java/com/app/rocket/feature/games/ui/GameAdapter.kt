package com.app.rocket.feature.games.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.app.rocket.R
import com.app.rocket.databinding.ItemGameBinding
import com.app.rocket.feature.games.data.models.Game
import com.app.rocket.feature.games.data.models.Image
import com.bumptech.glide.Glide

class GameAdapter(
    private val onGameItemClicked: (Game) -> Unit,
    private val resources: Resources
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<Game> = listOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, int: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GameViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        with(circularProgressDrawable) {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        when (holder) {
            is GameViewHolder -> {
                holder.binding?.let {
                    it.game = data[position]

                    Glide.with(holder.itemView.context)
                        .load(data[position].image?.icon_url ?: "")
                        .placeholder(circularProgressDrawable)
                        .error(ResourcesCompat.getDrawable(resources, R.drawable.ic_loading, null))
                        .into(it.image)
                }
            }
        }
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(data: List<Game>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemGameBinding? = DataBindingUtil.bind(view)

        init {
            binding?.let {
                it.parentContainer.setOnClickListener {
                    onGameItemClicked(binding.game ?: Game(name = "", description = "", image = (Image(icon_url = ""))))
                }
            }
        }
    }
}
