package id.allana.gamesapp.ui.detailgame

import androidx.lifecycle.LiveData
import id.allana.gamesapp.base.BaseContract
import id.allana.gamesapp.base.Resource
import id.allana.gamesapp.data.model.FavoriteGameEntity
import id.allana.gamesapp.data.model.ResponseDetailGame

interface DetailGameContract {

    interface View: BaseContract.BaseView {
        fun getDataDetail(id: Int)
        fun setDataDetail(data: ResponseDetailGame)
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getDetailGame(id: Int)
        fun getDetailGameLiveData(): LiveData<Resource<ResponseDetailGame>>

        /**
         * favorite game
         */
        fun getFavoriteGameById(id: Int)
        fun getFavoriteGameByIdLiveData(): LiveData<Boolean>
        fun deleteGame(id: Int)
        fun deleteGameLiveData(): LiveData<Resource<Int>>
        fun insertGame(game: FavoriteGameEntity)
        fun insertGameLiveData(): LiveData<Resource<Long>>
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getDetailGame(id: Int): ResponseDetailGame
        suspend fun getFavoriteGameByIdLiveData(id: Int): Boolean
        suspend fun deleteGame(id: Int): Int
        suspend fun insertGame(game: FavoriteGameEntity): Long
    }
}