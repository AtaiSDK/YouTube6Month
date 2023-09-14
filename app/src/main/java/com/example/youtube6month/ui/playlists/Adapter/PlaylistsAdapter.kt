package com.example.youtube6month.ui.playlists.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.databinding.ItemPlaylistsBinding


class PlaylistsAdapter(
    private val context: Context,
    private val onItemClick: (PlaylistsModel.Item) -> Unit,
) : PagingDataAdapter<PlaylistsModel.Item, PlaylistViewHolder>(PlaylistsDiffUtil) {

    private var playlists = mutableListOf<PlaylistsModel.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = playlists.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { playlistsItem ->
            holder.onBind(
                playlistsModelItem = playlistsItem,
                onItemClick = onItemClick,
                context = context,
            )
        }
    }
}