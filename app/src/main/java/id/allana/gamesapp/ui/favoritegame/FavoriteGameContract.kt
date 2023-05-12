package id.allana.gamesapp.ui.favoritegame

import androidx.lifecycle.MutableLiveData
import id.allana.gamesapp.base.BaseContract
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.model.FavoriteGameEntity

interface FavoriteGameContract {

    interface View: BaseContract.BaseView {
        fun initList()
        fun setDataAdapter(game: List<FavoriteGameEntity>)
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getFavoriteGame()
        fun getFavoriteGameLiveData(): MutableLiveData<Resource<List<FavoriteGameEntity>>>
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getFavoriteGame(): List<FavoriteGameEntity>
    }
}