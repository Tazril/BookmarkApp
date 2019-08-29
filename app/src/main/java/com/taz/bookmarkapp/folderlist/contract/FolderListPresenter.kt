package com.taz.bookmarkapp.folderlist.contract

import com.taz.bookmarkapp.folderlist.model.FolderListProvider
import com.taz.bookmarkapp.folderlist.model.FolderListResponseModel
import com.taz.bookmarkapp.helpers.PresenterCallback
import com.taz.bookmarkapp.mvp.BasePresenter

class FolderListPresenter(
    view: FolderListView,
    private val folderListProvider: FolderListProvider
) : BasePresenter<FolderListResponseModel>(view, folderListProvider) {

    fun deleteFolderPresenterResponse(folderId: Int) {
        view.showProgressBar()
        folderListProvider.deleteFolderProviderResponse(folderId, object : PresenterCallback<String> {
            override fun onSucces(responseModel: String) {
                view.hideProgressBar()
                view show responseModel
                (view as FolderListView).onFolderDeleted()
            }

            override fun onFailure(message: String) {
                view show message
                view.hideProgressBar()
            }
        })

    }
}