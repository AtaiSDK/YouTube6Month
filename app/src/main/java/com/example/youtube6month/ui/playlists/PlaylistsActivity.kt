package com.example.youtube6month.ui.playlists

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.youtube6month.core.base.BaseActivity
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.databinding.ActivityPlaylistsBinding
import com.example.youtube6month.ui.details.DetailsActivity
import com.example.youtube6month.ui.playlists.Adapter.PlaylistsAdapter
import com.example.youtube6month.ui.playlists.paging_load_states.PlaylistsLoadStateAdapter
import com.example.youtube6month.utils.Constants
import com.example.youtube6month.utils.sendData
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Array.get


class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    override fun inflateViewBinding(): ActivityPlaylistsBinding =
        ActivityPlaylistsBinding.inflate(layoutInflater)

    override val viewModel: PlaylistsViewModel by viewModel()

    private val adapter = PlaylistsAdapter(get(), this::onItemClick)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
    }

    override fun initLiveData() {
        super.initLiveData()


        lifecycleScope.launch {
            viewModel.getPlayList().observe(this@PlaylistsActivity) { pagingData ->
                binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    footer = PlaylistsLoadStateAdapter {},
                    header = PlaylistsLoadStateAdapter {},
                )
                adapter.submitData(
                    lifecycle = lifecycle, pagingData = pagingData
                )
            }
        }

        viewModel.loading.observe(this) { status ->
            if (status) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }
    }

    override fun initListener() {
        super.initListener()
        binding.noInternet.btnTryAgain.setOnClickListener {
            checkInternetConnection()
            initLiveData()
        }
    }

    override fun checkInternetConnection() {
        super.checkInternetConnection()
        viewModel.isOnline(this).observe(this) { isOnline ->
            if (!isOnline) {
                binding.noInternet.root.visibility = View.VISIBLE
            }else{
                binding.noInternet.root.visibility = View.GONE
            }
        }
    }

    private fun onItemClick(playlistsModel: PlaylistsModel.Item) =
        sendData(DetailsActivity(), Constants.PLAYLIST_KEY, playlistsModel)

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}