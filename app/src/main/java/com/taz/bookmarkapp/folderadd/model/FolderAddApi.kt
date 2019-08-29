package com.taz.bookmarkapp.folderadd.model

import com.taz.bookmarkapp.Folder
import com.taz.bookmarkapp.helpers.Urls
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FolderAddApi {

    @FormUrlEncoded
    @POST(Urls.FOLDER_LIST)
    fun postFolderResponse(@Field("name") name: String): Single<Folder>
}
