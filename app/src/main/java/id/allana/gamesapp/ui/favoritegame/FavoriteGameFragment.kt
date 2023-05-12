package id.allana.gamesapp.ui.favoritegame

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.allana.gamesapp.base.BaseFragment
import id.allana.gamesapp.base.GenericViewModelFactory
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.datasource.datalocal.FavoriteGamesDataSourceImpl
import id.allana.gamesapp.data.local.FavoriteGameDatabase
import id.allana.gamesapp.data.model.FavoriteGameEntity
import id.allana.gamesapp.databinding.FragmentFavoriteGameBinding


class FavoriteGameFragment : BaseFragment<FragmentFavoriteGameBinding, FavoriteGameViewModel>(
    FragmentFavoriteGameBinding::inflate
), FavoriteGameContract.View {

    private lateinit var favoriteAdapter: FavoriteGameAdapter
    override fun initView() {
        initList()
    }

    override fun initViewModel(): FavoriteGameViewModel {
        val dataSource = FavoriteGamesDataSourceImpl(FavoriteGameDatabase.getInstance(requireContext()).favoriteGameDao())
        val repository = FavoriteGameRepository(dataSource)
        return GenericViewModelFactory(FavoriteGameViewModel(repository)).create(FavoriteGameViewModel::class.java)
    }

    override fun initList() {
        favoriteAdapter = FavoriteGameAdapter()
        getViewBinding().rvFavoriteGame.apply {
            adapter = this@FavoriteGameFragment.favoriteAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun observeData() {
        getViewModel().getFavoriteGameLiveData().observe(viewLifecycleOwner) { resource ->
            when(resource) {
                is Resource.Loading -> {
                    showLoading(true)
                    showData(false)
                    showError(false)
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
        getViewBinding().rvFavoriteGame.isVisible = isVisible
    }

    override fun showLoading(isLoading: Boolean) {
        getViewBinding().progressBar.isVisible = isLoading
    }

    override fun showError(isError: Boolean, message: String?) {
        if (isError) Snackbar.make(requireView().rootView, message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun setDataAdapter(game: List<FavoriteGameEntity>) {
        this@FavoriteGameFragment.favoriteAdapter.setListItems(game)
    }


}