package com.taz.bookmarkapp.bookmarklist.contract

import com.taz.bookmarkapp.bookmarklist.model.BookmarkListProvider
import com.taz.bookmarkapp.bookmarklist.model.BookmarkListResponseModel
import com.taz.bookmarkapp.helpers.PresenterCallback
import com.taz.bookmarkapp.mvp.BasePresenter

class BookmarkListPresenter(
    view: BookmarkListView,
    provider: BookmarkListProvider
) : BasePresenter<BookmarkListResponseModel>(view, provider) {

    fun deleteBookmarkPresenterResponse(folderId: Int) {
        view.showProgressBar()
        (provider as BookmarkListProvider).deleteFolderProviderResponse(folderId, object : PresenterCallback<String> {
            override fun onSucces(responseModel: String) {
                view.hideProgressBar()
                view show responseModel
                (view as BookmarkListView).onBookmarkDeleted()
            }

            override fun onFailure(message: String) {
                view show message
                view.hideProgressBar()
            }
        })

    }
}