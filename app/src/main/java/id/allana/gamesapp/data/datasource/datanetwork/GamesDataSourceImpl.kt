package id.allana.gamesapp.data.datasource.datanetwork

import id.allana.gamesapp.data.model.ResponseDetailGame
import id.allana.gamesapp.data.model.ResponseSearchGame
import id.allana.gamesapp.data.network.ApiConfig

class GamesDataSourceImpl: GamesDataSource {
    override suspend fun getSearchGames(query: String): ResponseSearchGame {
        return ApiConfig.getApiService().getSearchGames(query)
    }

    override suspend fun getDetailGame(id: Int): ResponseDetailGame {
        return ApiConfig.getApiService().getDetailGame(id)
    }

    override suspend fun getListGames(): ResponseSearchGame {
        return ApiConfig.getApiService().getListGames()
    }

}