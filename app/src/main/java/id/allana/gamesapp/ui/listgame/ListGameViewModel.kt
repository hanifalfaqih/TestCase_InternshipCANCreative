package id.allana.gamesapp.ui.listgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.allana.gamesapp.base.BaseViewModelImpl
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.model.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListGameViewModel(
    private val listGameRepository: ListGameRepository
): BaseViewModelImpl(), ListGameContract.ViewModel {

    private val listSearchGameLiveData = MutableLiveData<Resource<List<ResultsItem?>?>>()
    private val listGamesLiveData = MutableLiveData<Resource<List<ResultsItem?>?>>()

    override fun getSearchGame(query: String) {
        listSearchGameLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = listGameRepository.getSearchGame(query)
                viewModelScope.launch(Dispatchers.Main) {
                    listSearchGameLiveData.value = Resource.Success(response.results)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    listSearchGameLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    override fun getSearchGameLiveData(): LiveData<Resource<List<ResultsItem?>?>> = listSearchGameLiveData

    override fun getListGame() {
        listGamesLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = listGameRepository.getListGame()
                viewModelScope.launch(Dispatchers.Main) {
                    listGamesLiveData.value = Resource.Success(response.results)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    listGamesLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    override fun getListGameLiveData(): LiveData<Resource<List<ResultsItem?>?>> = listGamesLiveData

}