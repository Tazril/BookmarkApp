package com.taz.bookmarkapp.folderlist.model


import com.taz.bookmarkapp.Folder
import com.taz.bookmarkapp.helpers.Urls
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface FolderListApi {
    //TODO hardcode id
    @GET(Urls.FOLDER_LIST)
    fun getFoldersListResponse(): Single<List<Folder>>

    @DELETE(Urls.BOOKMARK_LIST_IN_FOLDER)
    fun deleteFolder(@Path(Urls.ID) id: Int): Call<ResponseBody>
}

