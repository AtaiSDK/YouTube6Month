package com.example.youtube6month.ui.details
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.youtube6month.R
import com.example.youtube6month.core.base.BaseActivity
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.databinding.ActivityDetailsBinding
import com.example.youtube6month.ui.player.PlayerActivity
import com.example.youtube6month.ui.playlists.paging_load_states.PlaylistsLoadStateAdapter
import com.example.youtube6month.utils.Constants
import com.example.youtube6month.utils.sendData
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.youtube6month.ui.playlists.Adapter.PlaylistsAdapter


class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {
    override fun inflateViewBinding(): ActivityDetailsBinding =
        ActivityDetailsBinding.inflate(layoutInflater)

    override val viewModel: DetailsViewModel by viewModel()

    private val model by lazy { intent.extras?.getSerializable(Constants.PLAYLIST_KEY) as PlaylistsModel.Item }

    private val adapter = PlaylistsAdapter(get(), this::onItemClick)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter

        with(binding) {
            tvPlaylistName.text = model.snippet.title
            tvDescription.text = model.snippet.description
            tvNumberOfVideos.text =
                model.contentDetails.itemCount.toString() + getString(R.string.video_series)
            btnPlay.setOnClickListener {
                sendData(PlayerActivity(), Constants.DETAIL_KEY, model)
            }
        }
    }

    override fun initLiveData() {
        super.initLiveData()

        lifecycleScope.launch {
            viewModel.getDetails(model.id).observe(this@DetailsActivity) { pagingData ->
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
        binding.btnBack.setOnClickListener {
            finish()
        }
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
        sendData(PlayerActivity(), Constants.DETAIL_KEY, playlistsModel)

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}