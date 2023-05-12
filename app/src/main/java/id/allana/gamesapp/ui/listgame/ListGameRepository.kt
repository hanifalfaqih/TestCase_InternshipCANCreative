package id.allana.gamesapp.ui.listgame

import id.allana.gamesapp.data.datasource.datanetwork.GamesDataSource
import id.allana.gamesapp.data.model.ResponseSearchGame

class ListGameRepository(
    private val gamesDataSource: GamesDataSource
): ListGameContract.Repository {
    override suspend fun getSearchGame(query: String): ResponseSearchGame {
        return gamesDataSource.getSearchGames(query)
    }

    override suspend fun getListGame(): ResponseSearchGame {
        return gamesDataSource.getListGames()
    }
}