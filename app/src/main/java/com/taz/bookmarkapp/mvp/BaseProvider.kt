package com.taz.bookmarkapp.mvp

import com.taz.bookmarkapp.helpers.PresenterCallback

abstract class BaseProvider<T> {

    abstract fun getProviderResponse(callback: PresenterCallback<T>)

}