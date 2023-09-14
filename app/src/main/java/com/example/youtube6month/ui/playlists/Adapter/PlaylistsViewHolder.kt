package com.example.youtube6month.ui.playlists.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtube6month.R
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.databinding.ItemPlaylistsBinding

class PlaylistViewHolder(
    private val binding: ItemPlaylistsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(
        playlistsModelItem: PlaylistsModel.Item,
        onItemClick: (PlaylistsModel.Item) -> Unit,
        context: Context,
    ) {
        with(binding) {
            tvPlaylistName.text = playlistsModelItem.snippet.title
            ivPlaylist.load(playlistsModelItem.snippet.thumbnails.medium.url?: "")
            if (playlistsModelItem.snippet.localized != null) {
                tvNumberOfVideos.text =
                    playlistsModelItem.contentDetails.itemCount.toString() + context.getString(R.string.video_series)
            } else {
                tvNumberOfVideos.text = context.getString(R.string._04_00)
                tvInIvPlaylist.text = ""
                tvInIvPlaylist.background.colorFilter = PorterDuffColorFilter(
                    Color.TRANSPARENT,
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
        itemView.setOnClickListener {
            onItemClick(
                playlistsModelItem
            )
        }
    }
}