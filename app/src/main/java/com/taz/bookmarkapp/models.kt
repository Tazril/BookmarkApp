package com.taz.bookmarkapp

import com.google.gson.annotations.SerializedName

data class Folder(
    val id: Int,
    val name: String,
    @SerializedName("bookmarks") val bookmarkList: List<Bookmark>?
)

data class Bookmark(
    val id: Int,
    val url: String
)