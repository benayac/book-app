package com.example.model

import com.google.gson.annotations.SerializedName



data class GenreResponse(

	@field:SerializedName("resource")
	val resource: List<ResourceItem>? = null

)

data class ResourceItem(

	@field:SerializedName("icon_url")
	val iconUrl: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
