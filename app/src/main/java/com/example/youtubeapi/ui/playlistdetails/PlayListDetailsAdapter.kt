package com.example.youtubeapi.ui.playlistdetails

import android.content.Context
import android.os.Build
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemPlaylistDetailsBinding
import com.example.youtubeapi.extensions.loadImage
import com.example.youtubeapi.model.Items
import com.example.youtubeapi.model.PlayList
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class PlayListDetailsAdapter(
    private val playList: PlayList,
    private val clickListener: (id: Items) -> Unit
):RecyclerView.Adapter<PlayListDetailsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlaylistDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList.items[position])
    }

    override fun getItemCount(): Int {
        return playList.items.size
    }

    inner class ViewHolder(private val containerView: ItemPlaylistDetailsBinding):
            RecyclerView.ViewHolder(containerView.root) {

        fun onBind(playList: Items) {
            containerView.tvTitleDetails.text=playList.snippet.title
            containerView.ivPlaylistDetails.loadImage(playList.snippet.thumbnails.default.url)
            val date = playList.snippet.publishedAt
            val zonedDateTime = ZonedDateTime.parse(date)
            val offsetDateTime = OffsetDateTime.parse(date)
            val localOffsetDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.ofHours(-2))
            val localZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Brazil/DeNoronha"))
            containerView.tvDescDetails.text= String.format(localOffsetDateTime.format(
                DateTimeFormatter.ofPattern("dd-MM-uuuu ")))
            itemView.setOnClickListener{
                clickListener(playList)
            }

        }
    }


}