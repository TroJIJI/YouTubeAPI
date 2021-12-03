package com.example.youtubeapi.ui.playlist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.example.youtubeapi.extensions.visible
import com.example.youtubeapi.model.Items
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.ui.playlistdetails.PlaylistDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("NotifyDataSetChanged")
class   PlayListActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: PlayListViewModel by viewModel()

    private lateinit var playList: PlayList
    private val adapter: PlayListAdapter by lazy { PlayListAdapter(playList, this::clickListener) }

    override fun setUI() {

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
                Toast.makeText(this, "No internet!!!", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }

    private fun initRecyclerView() {

        viewBinding.rvPlaylist.apply {
            layoutManager =
                LinearLayoutManager(this@PlayListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = this@PlayListActivity.adapter

        }

        adapter.notifyDataSetChanged()
    }

    override fun setupLiveData() {
        viewModel.getPlayList().observe(this) {
            if(it.body()!=null) {
                playList = it.body()!!
                initRecyclerView()
            }
        }
    }

    private fun clickListener(id: Items) {
        Intent(this, PlaylistDetailsActivity::class.java).apply {
            putExtra(PLAYLIST, id.id)
            startActivity(this)
        }
    }


    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUpClickListener() {
    }

    companion object {
        const val PLAYLIST = "playlist"
    }

}