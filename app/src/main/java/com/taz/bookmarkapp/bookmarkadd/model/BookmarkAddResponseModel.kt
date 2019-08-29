package com.taz.bookmarkapp.bookmarkadd.model

import com.taz.bookmarkapp.Bookmark


data class BookmarkAddResponseModel(val bookmark: Bookmark?, val status: String?)

data class BookmarkPostModel(val url: String, val folder: Int?)