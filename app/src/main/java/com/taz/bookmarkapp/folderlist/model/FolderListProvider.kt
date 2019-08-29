package com.taz.bookmarkapp.folderlist.model

import com.taz.bookmarkapp.helpers.ApiClient
import com.taz.bookmarkapp.helpers.Constant
import com.taz.bookmarkapp.helpers.PresenterCallback
import com.taz.bookmarkapp.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class FolderListProvider : BaseProvider<FolderListResponseModel>() {

    override fun getProviderResponse(callback: PresenterCallback<FolderListResponseModel>) {
        ApiClient.retroClient.create(FolderListApi::class.java).getFoldersListResponse()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { callback.onSucces(FolderListResponseModel(it, Constant.SUCCESS_MESSAGE)) },
                onError = { callback.onFailure(it.message.toString()) }
            )

    }

    fun deleteFolderProviderResponse(folderId: Int, callback: PresenterCallback<String>) {
        ApiClient.retroClient.create(FolderListApi::class.java).deleteFolder(folderId).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback.onFailure(Constant.DELETE_FAIL_MESSAGE)
                }

                override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                    if (response.isSuccessful)
                        callback.onSucces(Constant.DELETE_SUCCESS_MESSAGE)
                    else
                        callback.onFailure(response.message().toString())
                }

            }
        )


    }
}

