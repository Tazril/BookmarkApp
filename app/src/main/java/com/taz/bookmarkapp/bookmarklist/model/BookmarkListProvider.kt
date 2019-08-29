package com.taz.bookmarkapp.bookmarklist.model

import com.taz.bookmarkapp.Folder
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

class BookmarkListProvider(private val folderId: Int) : BaseProvider<BookmarkListResponseModel>() {

    override fun getProviderResponse(callback: PresenterCallback<BookmarkListResponseModel>) {
        if (folderId == Constant.ALL_BOOKMARK_ID) {
            ApiClient.retroClient.create(BookmarkListApi::class.java).getAllBookmarksListResponse()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        callback.onSucces(
                            BookmarkListResponseModel(
                                Folder(0, "All Bookmarks", it),
                                Constant.SUCCESS_MESSAGE
                            )
                        )
                    },
                    onError = { callback.onFailure(it.message.toString()) }
                )
        } else {
            ApiClient.retroClient.create(BookmarkListApi::class.java).getBookmarksListResponse(folderId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { callback.onSucces(BookmarkListResponseModel(it, Constant.SUCCESS_MESSAGE)) },
                    onError = { callback.onFailure(it.message.toString()) }
                )
        }

    }

    fun deleteFolderProviderResponse(bookmarkId: Int, callback: PresenterCallback<String>) {
        ApiClient.retroClient.create(BookmarkListApi::class.java).deleteBookmark(bookmarkId).enqueue(
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