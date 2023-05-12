package id.allana.gamesapp.ui.favoritegame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.allana.gamesapp.base.BaseViewModelImpl
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.model.FavoriteGameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteGameViewModel(private val favoriteGameRepository: FavoriteGameRepository): BaseViewModelImpl(), FavoriteGameContract.ViewModel {

    private val allFavoriteGameLiveData = MutableLiveData<Resource<List<FavoriteGameEntity>>>()


    override fun getFavoriteGame() {
        allFavoriteGameLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = favoriteGameRepository.getFavoriteGame()
                viewModelScope.launch(Dispatchers.Main) {
                    allFavoriteGameLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    allFavoriteGameLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    override fun getFavoriteGameLiveData(): MutableLiveData<Resource<List<FavoriteGameEntity>>> = allFavoriteGameLiveData
}