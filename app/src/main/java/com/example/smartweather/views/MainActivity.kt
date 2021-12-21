package com.example.smartweather.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartweather.databinding.ActivityMainBinding
import com.example.smartweather.models.RootService
import com.example.smartweather.mvvm.WeatherMvvm
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: WeatherMvvm
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = WeatherMvvm()
        initListener()
        getData()
    }


    fun initListener(){
        lifecycleScope.launchWhenCreated {
            viewModel.data.filterNotNull().collect { data ->
                setupRecycler(data)
            }
        }
    }

    private fun setupRecycler(data: RootService) {
        val listRecycler: MutableList<RootService> = arrayListOf()
        for(num in 1..30) {
            listRecycler.add(data)
        }

        val manager = LinearLayoutManager(this)
        binding.rv.apply {
            adapter =  WeatherAdapter(listRecycler)
            layoutManager = manager
           setHasFixedSize(true)
        }

    }

    fun getData(){
        viewModel.getData()
    }
}