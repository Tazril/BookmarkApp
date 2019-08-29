package com.taz.bookmarkapp.mvp

import com.taz.bookmarkapp.helpers.PresenterCallback


abstract class BasePresenter<T>(val view: BaseFragment<T>, val provider: BaseProvider<T>) {

    open fun getPresenterResponse() {
        view.showProgressBar()
        provider.getProviderResponse(object : PresenterCallback<T> {
            override fun onSucces(responseModel: T) {
                view.hideProgressBar()
                view.loadResponse(responseModel)
            }

            override fun onFailure(message: String) {
                view show message
                view.hideProgressBar()
            }
        })
    }

    open fun initPresenter() {
        view.initView()
    }
}