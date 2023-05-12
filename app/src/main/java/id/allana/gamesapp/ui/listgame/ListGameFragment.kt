package id.allana.gamesapp.ui.listgame

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.allana.gamesapp.R
import id.allana.gamesapp.base.BaseFragment
import id.allana.gamesapp.base.GenericViewModelFactory
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.datasource.datanetwork.GamesDataSourceImpl
import id.allana.gamesapp.data.model.ResultsItem
import id.allana.gamesapp.databinding.FragmentListGameBinding

class ListGameFragment : BaseFragment<FragmentListGameBinding, ListGameViewModel>(
    FragmentListGameBinding::inflate
), ListGameContract.View {

    private lateinit var adapter: ListGameAdapter

    override fun initView() {
        startingList()
        setupMenu()
        initList()
    }

    override fun initViewModel(): ListGameViewModel {
        val networkDataSource = GamesDataSourceImpl()
        val repository = ListGameRepository(networkDataSource)
        return GenericViewModelFactory(ListGameViewModel(repository)).create(ListGameViewModel::class.java)
    }

    override fun startingList() {
        getViewModel().getListGame()
    }

    override fun initList() {
        adapter = ListGameAdapter()
        getViewBinding().rvGame.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun setDataAdapter(listGame: List<ResultsItem?>) {
        this@ListGameFragment.adapter.setListItems(listGame)
        getViewBinding().rvGame.adapter = this@ListGameFragment.adapter
    }

    override fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)

                val search = menu.findItem(R.id.search_menu)
                val searchView = search.actionView as SearchView
                searchView.isSubmitButtonEnabled = true
                searchView.setOnQueryTextListener(object: OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            getViewModel().getSearchGame(query)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.favorite_menu) {
                    val actionFavorite = ListGameFragmentDirections.actionListGameFragmentToFavoriteGameFragment()
                    findNavController().navigate(actionFavorite)
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun observeData() {
        getViewModel().getSearchGameLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                    showData(false)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, "Failed get data!")
                    showData(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false)
                    showData(true)
                    resource.data?.let { setDataAdapter(it) }
                }
            }
        }
        getViewModel().getListGameLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                    showData(false)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, "Failed get data!")
                    showData(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false)
                    showData(true)
                    resource.data?.let { setDataAdapter(it) }
                }
            }
        }
    }

    override fun showData(isVisible: Boolean) {
        getViewBinding().rvGame.isVisible = isVisible
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) getViewBinding().progressBar.visibility = View.VISIBLE else getViewBinding().progressBar.visibility = View.INVISIBLE
    }

    override fun showError(isError: Boolean, message: String?) {
        if (isError) Snackbar.make(requireActivity().findViewById(android.R.id.content), message.toString(), Snackbar.LENGTH_SHORT).show()
    }
}