package com.taz.bookmarkapp.folderadd.contract

import com.taz.bookmarkapp.folderadd.model.FolderAddProvider
import com.taz.bookmarkapp.folderadd.model.FolderAddResponseModel
import com.taz.bookmarkapp.mvp.BasePresenter

class FolderAddPresenter(
    view: FolderAddView,
    provider: FolderAddProvider
) : BasePresenter<FolderAddResponseModel>(view, provider)