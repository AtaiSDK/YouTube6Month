package com.example.youtube6month.ui.playlists.Adapter
import androidx.recyclerview.widget.DiffUtil.*
import com.example.youtube6month.data.model.PlaylistsModel

object PlaylistsDiffUtil : ItemCallback<PlaylistsModel.Item>() {
    override fun areItemsTheSame(
        oldItem: PlaylistsModel.Item,
        newItem: PlaylistsModel.Item
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PlaylistsModel.Item,
        newItem: PlaylistsModel.Item
    ): Boolean {
        return oldItem == newItem
    }
}
