package id.allana.gamesapp.ui.detailgame

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import id.allana.gamesapp.R
import id.allana.gamesapp.base.BaseFragment
import id.allana.gamesapp.base.GenericViewModelFactory
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.datasource.datalocal.FavoriteGamesDataSourceImpl
import id.allana.gamesapp.data.datasource.datanetwork.GamesDataSourceImpl
import id.allana.gamesapp.data.local.FavoriteGameDatabase
import id.allana.gamesapp.data.model.FavoriteGameEntity
import id.allana.gamesapp.data.model.ResponseDetailGame
import id.allana.gamesapp.databinding.FragmentDetailGameBinding


class DetailGameFragment : BaseFragment<FragmentDetailGameBinding, DetailGameViewModel>(
    FragmentDetailGameBinding::inflate
), DetailGameContract.View {

    private var isFavorite = false
    private var idGame = 0

    override fun initView() {
        val dataId by navArgs<DetailGameFragmentArgs>()
        idGame = dataId.id.apply {
            getDataDetail(this)
            getStatusFavorite(this)
        }
    }

    private fun getStatusFavorite(id: Int) {
        getViewModel().getFavoriteGameById(id)
    }

    override fun initViewModel(): DetailGameViewModel {
        val networkDataSource = GamesDataSourceImpl()
        val localDataSource = FavoriteGamesDataSourceImpl(FavoriteGameDatabase.getInstance(requireContext()).favoriteGameDao())
        val repository = DetailGameRepository(networkDataSource, localDataSource)
        return GenericViewModelFactory(DetailGameViewModel(repository)).create(DetailGameViewModel::class.java)
    }

    override fun getDataDetail(id: Int) {
        getViewModel().getDetailGame(id)
    }

    override fun setDataDetail(data: ResponseDetailGame) {
        Glide.with(requireContext())
            .load(data.backgroundImage)
            .into(getViewBinding().ivPosterGame)

        getViewBinding().tvDescriptionGame.text = data.description_raw
        getViewBinding().tvRatingGame.text = data.rating.toString()
        getViewBinding().tvTitleGame.text = data.name

        val listDataDeveloper = arrayListOf<String>()
        for (developer in data.developers!!) {
            listDataDeveloper.add(developer?.name!!)
        }

        getViewBinding().tvDeveloperGame.text = listDataDeveloper.toString()

        getViewBinding().tvReleaseDateGame.text = data.released
        /**
         * handle user when click fab
         * insert data to favorite or delete data from favorite
         * change icon fab when clicked
         */
        getViewBinding().fabFavorite.setOnClickListener {
            if (isFavorite) {
                getViewModel().deleteGame(this@DetailGameFragment.id)
                isFavorite = !isFavorite
                checkDataFavorite(isFavorite)
            } else {
                getViewModel().insertGame(FavoriteGameEntity(name = data.name, rating = data.rating.toString(), released = data.released, backgroundImage = data.backgroundImage))
                isFavorite = !isFavorite
                checkDataFavorite(isFavorite)
            }
        }
    }


    override fun observeData() {
        getViewModel().getDetailGameLiveData().observe(viewLifecycleOwner) { resource ->
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
                    resource.data?.let { setDataDetail(it) }
                }
            }
        }
        getViewModel().insertGameLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    showError(true, "Add to favorite")
                }
                is Resource.Error -> {
                    showError(true, "Failed add to favorite")
                }
            }
        }
        getViewModel().deleteGameLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    showError(true, "Delete from favorite")
                }
                is Resource.Error -> {
                    showError(true, "Failed delete from favorite")
                }
            }
        }

        /**
         * check if any data in favorite
         * use id to filter data
         * set value isFavorite true or false
         */
        getViewModel().getFavoriteGameByIdLiveData().observe(viewLifecycleOwner) { isFavorite ->
            this.isFavorite = isFavorite
            checkDataFavorite(isFavorite)
        }
    }

    private fun checkDataFavorite(favorite: Boolean?) {
        if (favorite == true) getViewBinding().fabFavorite.setImageResource(R.drawable.ic_favorite_game) else getViewBinding().fabFavorite.setImageResource(R.drawable.ic_favorite_game_border)
    }

    override fun showData(isVisible: Boolean) {
        getViewBinding().ivPosterGame.isVisible = isVisible
        getViewBinding().tvDescriptionGame.isVisible = isVisible
        getViewBinding().tvRatingGame.isVisible = isVisible
        getViewBinding().tvTitleGame.isVisible = isVisible
        getViewBinding().tvDeveloperGame.isVisible = isVisible
        getViewBinding().tvReleaseDateGame.isVisible = isVisible
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) getViewBinding().progressBar.visibility = View.VISIBLE else getViewBinding().progressBar.visibility = View.INVISIBLE
    }

    override fun showError(isError: Boolean, message: String?) {
        if (isError) Snackbar.make(requireActivity().findViewById(android.R.id.content), message.toString(), Snackbar.LENGTH_SHORT).show()
    }
}