package com.appezzy.app.ui.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.appezzy.app.R
import com.appezzy.app.databinding.FragmentNewsBinding
import com.appezzy.app.ui.home.MainActivity

class NewsFragment : Fragment() {
    private val parent: MainActivity by lazy { activity as MainActivity }
    private lateinit var binding: FragmentNewsBinding

    private val viewModel: NewsViewModel by lazy { NewsViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false).apply {
            viewModel = this@NewsFragment.viewModel
            lifecycleOwner = this@NewsFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observe()
    }



    private fun init() {
        binding.recyclerView.adapter = NewsAdapter(parent)
        viewModel.listNews()
        binding.swipeReflesh.setOnRefreshListener {
            viewModel.listNews()
        }
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.swipeReflesh.isRefreshing = it
        }

        viewModel.actionState.observe(viewLifecycleOwner) {

        }
    }
}