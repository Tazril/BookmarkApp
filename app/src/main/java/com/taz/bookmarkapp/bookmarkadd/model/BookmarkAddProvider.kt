package com.taz.bookmarkapp.bookmarkadd.model

import com.taz.bookmarkapp.helpers.ApiClient
import com.taz.bookmarkapp.helpers.Constant
import com.taz.bookmarkapp.helpers.PresenterCallback
import com.taz.bookmarkapp.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BookmarkAddProvider(private val bookmarkUrl: String, private var folderId: Int?) : BaseProvider<BookmarkAddResponseModel>() {


    override fun getProviderResponse(callback: PresenterCallback<BookmarkAddResponseModel>) {
        if (folderId == Constant.ALL_BOOKMARK_ID) folderId = null
        ApiClient.retroClient.create(BookmarkAddApi::class.java)
            .postBookmarkResponse(BookmarkPostModel(bookmarkUrl, folderId))
            .subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribeBy(
                onSuccess = { callback.onSucces(BookmarkAddResponseModel(it, "Bookmark Created")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }


}