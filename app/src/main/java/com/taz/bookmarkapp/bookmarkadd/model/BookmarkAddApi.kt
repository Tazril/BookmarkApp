package com.taz.bookmarkapp.bookmarkadd.model

import com.taz.bookmarkapp.Bookmark
import com.taz.bookmarkapp.helpers.Urls
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BookmarkAddApi {

    @POST(Urls.BOOKMARK_LIST)
    fun postBookmarkResponse(@Body obj: BookmarkPostModel): Single<Bookmark>
}
