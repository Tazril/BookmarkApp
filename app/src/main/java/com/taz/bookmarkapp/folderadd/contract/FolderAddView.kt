package com.taz.bookmarkapp.folderadd.contract

import android.annotation.SuppressLint
import android.os.Bundle
import com.taz.bookmarkapp.R
import com.taz.bookmarkapp.folderadd.model.FolderAddProvider
import com.taz.bookmarkapp.folderadd.model.FolderAddResponseModel
import com.taz.bookmarkapp.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_folder_add.*


class FolderAddView : BaseFragment<FolderAddResponseModel>() {


    @SuppressLint("SetTextI18n")
    override fun loadResponse(responseModel: FolderAddResponseModel) {
        textViewInfo.text = "Created ${responseModel.folder!!.name}"
    }

    override val layoutId: Int = R.layout.fragment_folder_add
    private lateinit var presenter: FolderAddPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        saveBtn.setOnClickListener {
            hideKeyboard()
            if (editTextName.text!!.toString().isNotEmpty()) {
                presenter = FolderAddPresenter(this, FolderAddProvider(editTextName.text.toString()))
                presenter.initPresenter()
            }
        }


    }

    override fun initView() {
        presenter.getPresenterResponse()
    }


}