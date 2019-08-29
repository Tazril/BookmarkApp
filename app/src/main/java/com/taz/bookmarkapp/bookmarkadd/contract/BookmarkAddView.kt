package com.taz.bookmarkapp.bookmarkadd.contract

import android.annotation.SuppressLint
import android.os.Bundle
import com.taz.bookmarkapp.R
import com.taz.bookmarkapp.bookmarkadd.model.BookmarkAddProvider
import com.taz.bookmarkapp.bookmarkadd.model.BookmarkAddResponseModel
import com.taz.bookmarkapp.helpers.Constant
import com.taz.bookmarkapp.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_folder_add.*


class BookmarkAddView : BaseFragment<BookmarkAddResponseModel>() {


    @SuppressLint("SetTextI18n")
    override fun loadResponse(responseModel: BookmarkAddResponseModel) {
        textViewInfo.text = "Created ${responseModel.bookmark!!.url}"
    }

    override val layoutId: Int = R.layout.fragment_folder_add
    private lateinit var presenter: BookmarkAddPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val folderId = arguments!!.getInt(Constant.ID_KEY)
        saveBtn.setOnClickListener {
            hideKeyboard()
            if (editTextName.text!!.toString().isNotEmpty()) {
                presenter = BookmarkAddPresenter(this, BookmarkAddProvider(editTextName.text.toString(), folderId))
                presenter.initPresenter()
            }
        }


    }

    override fun initView() {
        presenter.getPresenterResponse()
    }


}