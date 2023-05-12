package id.allana.gamesapp.ui.detailgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.allana.gamesapp.base.BaseViewModelImpl
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.model.FavoriteGameEntity
import id.allana.gamesapp.data.model.ResponseDetailGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailGameViewModel(
    private val detailGameRepository: DetailGameRepository
): BaseViewModelImpl(), DetailGameContract.ViewModel {

    private val detailGameLiveData = MutableLiveData<Resource<ResponseDetailGame>>()
    private val deleteGameLiveData = MutableLiveData<Resource<Int>>()
    private val insertGameLiveData = MutableLiveData<Resource<Long>>()
    private val favoriteGameLiveData = MutableLiveData<Boolean>()

    override fun getDetailGame(id: Int) {
        detailGameLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = detailGameRepository.getDetailGame(id)
                viewModelScope.launch(Dispatchers.Main) {
                    detailGameLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    detailGameLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    override fun getDetailGameLiveData(): LiveData<Resource<ResponseDetailGame>> = detailGameLiveData

    override fun getFavoriteGameById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = detailGameRepository.getFavoriteGameByIdLiveData(id)
            viewModelScope.launch(Dispatchers.Main) {
                favoriteGameLiveData.value = response
            }
        }
    }

    override fun getFavoriteGameByIdLiveData(): LiveData<Boolean> = favoriteGameLiveData

    override fun deleteGame(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = detailGameRepository.deleteGame(id)
                viewModelScope.launch(Dispatchers.Main) {
                    deleteGameLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    deleteGameLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun deleteGameLiveData(): LiveData<Resource<Int>> = deleteGameLiveData

    override fun insertGame(game: FavoriteGameEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = detailGameRepository.insertGame(game)
                viewModelScope.launch(Dispatchers.Main) {
                    insertGameLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    insertGameLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun insertGameLiveData(): LiveData<Resource<Long>> = insertGameLiveData
}