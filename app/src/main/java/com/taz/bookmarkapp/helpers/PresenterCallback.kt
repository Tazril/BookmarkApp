package com.taz.bookmarkapp.helpers

interface PresenterCallback<T> {
    fun onSucces(responseModel: T)
    fun onFailure(message: String)
}

