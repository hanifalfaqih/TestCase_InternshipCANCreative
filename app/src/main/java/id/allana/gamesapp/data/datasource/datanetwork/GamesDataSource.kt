package id.allana.gamesapp.data.datasource.datanetwork

import id.allana.gamesapp.data.model.ResponseDetailGame
import id.allana.gamesapp.data.model.ResponseSearchGame

interface GamesDataSource {

    suspend fun getSearchGames(query: String): ResponseSearchGame

    suspend fun getDetailGame(id: Int): ResponseDetailGame

    suspend fun getListGames(): ResponseSearchGame
}