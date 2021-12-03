package com.example.youtubeapi.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.databinding.ItemPlaylistBinding
import com.example.youtubeapi.extensions.loadImage
import com.example.youtubeapi.model.Items
import com.example.youtubeapi.model.PlayList

class PlayListAdapter(
    private val playList: PlayList,
    private val clickListener: (id:Items)->Unit
) : RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList.items[position])
    }

    override fun getItemCount(): Int {
        return playList.items.size
    }

    inner class ViewHolder(private val containerView: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(containerView.root) {

        fun onBind(playList: Items) {
            containerView.tvTitle.text = playList.snippet.title
            containerView.ivPlaylist.loadImage(playList.snippet.thumbnails.default.url)

            containerView.tvDesc.text = String.format("${playList.contentDetails.itemCount} ${itemView.context.getString(R.string.video_series)}")
            itemView.setOnClickListener {
                clickListener(playList)
            }

        }
    }
}
