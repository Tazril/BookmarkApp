package com.taz.bookmarkapp.folderadd.model

import com.taz.bookmarkapp.helpers.ApiClient
import com.taz.bookmarkapp.helpers.PresenterCallback
import com.taz.bookmarkapp.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FolderAddProvider(private val folderName: String) : BaseProvider<FolderAddResponseModel>() {


    override fun getProviderResponse(callback: PresenterCallback<FolderAddResponseModel>) {
        ApiClient.retroClient.create(FolderAddApi::class.java).postFolderResponse(folderName)
            .subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribeBy(
                onSuccess = { callback.onSucces(FolderAddResponseModel(it, "Folder Created")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }


}