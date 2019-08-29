package com.taz.bookmarkapp.bookmarklist.model


import com.taz.bookmarkapp.Bookmark
import com.taz.bookmarkapp.Folder
import com.taz.bookmarkapp.helpers.Urls
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface BookmarkListApi {
    //TODO hardcode id
    @GET(Urls.BOOKMARK_LIST_IN_FOLDER)
    fun getBookmarksListResponse(@Path(Urls.ID) id: Int): Single<Folder>

    @GET(Urls.BOOKMARK_LIST)
    fun getAllBookmarksListResponse(): Single<List<Bookmark>>

    @DELETE(Urls.BOOKMARK)
    fun deleteBookmark(@Path(Urls.ID) id: Int): Call<ResponseBody>
}

