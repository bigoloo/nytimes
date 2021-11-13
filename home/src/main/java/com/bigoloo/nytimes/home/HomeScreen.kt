package com.bigoloo.nytimes.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.bigoloo.nytimes.home.databinding.ScreenHomeBinding
import com.bigoloo.nytimes.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeScreen : Fragment() {

    private var _binding: ScreenHomeBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val homeViewModel: HomeViewModel by viewModel<HomeViewModel>()

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}