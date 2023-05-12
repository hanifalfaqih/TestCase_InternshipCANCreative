package id.allana.gamesapp.ui.listgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.allana.gamesapp.base.BaseContract
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.model.ResponseSearchGame
import id.allana.gamesapp.data.model.ResultsItem

interface ListGameContract {

    interface View: BaseContract.BaseView {
        fun startingList()
        fun initList()
        fun setDataAdapter(listGame: List<ResultsItem?>)
        fun setupMenu()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getSearchGame(query: String)
        fun getSearchGameLiveData(): LiveData<Resource<List<ResultsItem?>?>>
        fun getListGame()
        fun getListGameLiveData(): LiveData<Resource<List<ResultsItem?>?>>
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getSearchGame(query: String): ResponseSearchGame
        suspend fun getListGame(): ResponseSearchGame
    }
}