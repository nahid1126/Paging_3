package com.nahid.paging3kotlin.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nahid.paging3kotlin.R
import com.nahid.paging3kotlin.databinding.ActivityMainBinding
import com.nahid.paging3kotlin.view.adapter.LoaderAdapter
import com.nahid.paging3kotlin.view.adapter.QuotesAdapter
import com.nahid.paging3kotlin.view_model.QuoteViewModel
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var quotesAdapter: QuotesAdapter
    private lateinit var quoteViewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        quotesAdapter = QuotesAdapter()

        mainBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = quotesAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(), footer = LoaderAdapter()
            )
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            quoteViewModel.quoteList.observe(this@MainActivity) {
                quotesAdapter.submitData(lifecycle, it)
            }
        }
    }
}