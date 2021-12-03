package com.example.youtubeapi.ui.playlistdetails

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistDetailsBinding
import com.example.youtubeapi.extensions.visible
import com.example.youtubeapi.model.Items
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.ui.playlist.PlayListActivity.Companion.PLAYLIST
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistDetailsActivity : BaseActivity<ActivityPlaylistDetailsBinding>() {
    private lateinit var playlist: PlayList
    private val viewModel: PlayListDetailsViewModel by viewModel()
    private val myAdapter: PlayListDetailsAdapter by lazy {
        PlayListDetailsAdapter(playlist, this::clickListener)
    }

    private fun clickListener(id: Items) {


    }

    override fun checkInternet(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            } else {
                viewBinding.errorInternet.visible()
                Toast.makeText(this, "No internet!!!", Toast.LENGTH_LONG).show()
            }

        }
        return false
    }


    override fun setUI() {

    }

    override fun setupLiveData() {

        viewModel.getPlaylistDetails(intent.getStringExtra(PLAYLIST).toString()).observe(this) {
            if (it.body() != null) {
                playlist = it.body()!!
                initRecycler()
            }
        }
    }

    private fun initRecycler() {
        viewBinding.rvPlaylistDetails.apply {
            layoutManager = LinearLayoutManager(
                this@PlaylistDetailsActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = this@PlaylistDetailsActivity.myAdapter
        }
        myAdapter.notifyDataSetChanged()
    }

    override fun inflateViewBinding(): ActivityPlaylistDetailsBinding {
        return ActivityPlaylistDetailsBinding.inflate(layoutInflater)
    }

    override fun setUpClickListener() {
    }

}