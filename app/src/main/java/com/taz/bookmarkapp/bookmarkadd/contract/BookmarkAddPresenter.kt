package com.taz.bookmarkapp.bookmarkadd.contract

import com.taz.bookmarkapp.bookmarkadd.model.BookmarkAddProvider
import com.taz.bookmarkapp.bookmarkadd.model.BookmarkAddResponseModel
import com.taz.bookmarkapp.mvp.BasePresenter

class BookmarkAddPresenter(
    view: BookmarkAddView,
    provider: BookmarkAddProvider
) : BasePresenter<BookmarkAddResponseModel>(view, provider)