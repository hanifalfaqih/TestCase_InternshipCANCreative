package id.allana.gamesapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseSearchGame(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("user_platforms")
	val userPlatforms: Boolean? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class ResultsItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,
)
