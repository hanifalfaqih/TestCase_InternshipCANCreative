package id.allana.gamesapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailGame(

	@field:SerializedName("developers")
	val developers: List<DevelopersItem?>? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description_raw")
	val description_raw: String? = null,

)


data class DevelopersItem(

	@field:SerializedName("name")
	val name: String? = null,

)