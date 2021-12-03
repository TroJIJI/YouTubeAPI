package com.example.youtubeapi.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var viewBinding: VB
    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding()
        setContentView(viewBinding.root)
        checkInternet(this)
        setUI()
        setupLiveData()
        setUpClickListener()

    }

    abstract fun setUpClickListener()


    abstract fun setUI() // инициализация UI

    /*abstract fun checkInternet(context: Context)*/

    abstract fun setupLiveData()

    abstract fun checkInternet(context: Context?): Boolean
}