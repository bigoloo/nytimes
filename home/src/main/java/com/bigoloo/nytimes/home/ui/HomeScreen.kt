package com.bigoloo.nytimes.home.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.bigoloo.nytimes.home.R
import com.bigoloo.nytimes.home.databinding.ScreenHomeBinding
import com.bigoloo.nytimes.home.models.Failed
import com.bigoloo.nytimes.home.models.Loaded
import com.bigoloo.nytimes.home.models.Loading
import com.bigoloo.nytimes.home.models.NotLoaded
import com.bigoloo.nytimes.home.ui.adaptor.DiffCallback
import com.bigoloo.nytimes.home.ui.adaptor.StoryAdaptor
import com.bigoloo.nytimes.home.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeScreen : Fragment() {

    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!

    private var storyAdaptor: StoryAdaptor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.search_action).actionView as SearchView
        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                homeViewModel.doSearch(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storyAdaptor = StoryAdaptor(DiffCallback())
        binding.screenHomeRetryAgain.setOnClickListener {
            homeViewModel.doSearch(null)
        }
        binding.screenHomeStoryList.adapter = storyAdaptor
        binding.screenHomeStoryList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        observeState()
    }

    override fun onResume() {
        super.onResume()
     modifyActionBar()
    }
    private fun modifyActionBar() {
        val actionbar = (requireActivity() as AppCompatActivity).supportActionBar!!
        actionbar.title = getString(R.string.top_stories)

    }

    private fun observeState() {
        lifecycleScope.launch {
            homeViewModel.state.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect { state ->
                withContext(Dispatchers.Main.immediate) {
                    when (state) {
                        is Failed -> {
                            binding.screenHomeFailedTitle.isVisible = true
                            binding.screenHomeLoading.isVisible = false
                            binding.screenHomeRetryAgain.isVisible = true
                            binding.screenHomeStoryList.isVisible = false

                        }
                        is Loaded -> {
                            binding.screenHomeFailedTitle.isVisible = false
                            binding.screenHomeLoading.isVisible = false
                            binding.screenHomeRetryAgain.isVisible = false
                            binding.screenHomeStoryList.isVisible = true
                            storyAdaptor!!.submitList(state.data)
                        }
                        Loading -> {
                            binding.screenHomeFailedTitle.isVisible = false
                            binding.screenHomeLoading.isVisible = true
                            binding.screenHomeRetryAgain.isVisible = false
                            binding.screenHomeStoryList.isVisible = false
                        }
                        NotLoaded -> {
                            binding.screenHomeFailedTitle.isVisible = false
                            binding.screenHomeLoading.isVisible = false
                            binding.screenHomeRetryAgain.isVisible = false
                            binding.screenHomeStoryList.isVisible = false

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        storyAdaptor = null
    }
}