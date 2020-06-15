package com.example.model

import com.google.gson.annotations.SerializedName

data class LatestBookRespone(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("time")
	val time: Time? = null
)

data class UserByUserId(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ResultItem(

	@field:SerializedName("Writer_by_writer_id")
	val writerByWriterId: WriterByWriterId? = null,

	@field:SerializedName("cover_url")
	val coverUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("book_id")
	val bookId: Int? = null,

	@field:SerializedName("isNew")
	val isNew: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("schedule_task")
	val scheduleTask: String? = null,

	@field:SerializedName("is_update")
	val isUpdate: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: Any? = null,

	@field:SerializedName("rate_sum")
	val rateSum: Float? = null,

	@field:SerializedName("writer_id")
	val writerId: Int? = null,

	@field:SerializedName("view_count")
	val viewCount: Int? = null,

	@field:SerializedName("chapter_count")
	val chapterCount: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Time(

	@field:SerializedName("viewer")
	val viewer: Double? = null,

	@field:SerializedName("chapter")
	val chapter: Double? = null,

	@field:SerializedName("book_official")
	val bookOfficial: Double? = null,

	@field:SerializedName("chapter_book")
	val chapterBook: Double? = null,

	@field:SerializedName("chapter_new")
	val chapterNew: Double? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("user")
	val user: Double? = null
)

data class WriterByWriterId(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("schedule_task")
	val scheduleTask: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("User_by_user_id")
	val userByUserId: UserByUserId? = null,

	@field:SerializedName("status")
	val status: Any? = null,

	@field:SerializedName("royalty_id")
	val royaltyId: Any? = null
)
