package com.taz.bookmarkapp.folderlist.contract

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.taz.bookmarkapp.Folder
import com.taz.bookmarkapp.R
import com.taz.bookmarkapp.folderlist.model.FolderListProvider
import com.taz.bookmarkapp.folderlist.model.FolderListResponseModel
import com.taz.bookmarkapp.helpers.Constant
import com.taz.bookmarkapp.mvp.BaseListFragment
import kotlinx.android.synthetic.main.fragment_folder_list.*
import kotlinx.android.synthetic.main.fragment_folder_list.view.*

class FolderListView : BaseListFragment<FolderListResponseModel, Folder, FolderListAdapter>() {

    override val adapter: FolderListAdapter = FolderListAdapter()
    override lateinit var recyclerView: RecyclerView


    override fun loadResponse(responseModel: FolderListResponseModel) {
        adapter.list = responseModel.foldersList!!
        list = adapter.list.toMutableList()
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = recyclerViewFolderListFragment
        super.onViewCreated(view, savedInstanceState)
    }

    override val layoutId: Int = R.layout.fragment_folder_list
    private lateinit var presenter: FolderListPresenter
    private lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = FolderListPresenter(this, FolderListProvider())
        presenter.initPresenter()
        navController = view!!.findNavController()
        val searchView = view!!.searchViewFolderList
        searchView.hasListener { o, s -> o.name.toLowerCase().contains(s) }
        adapter.listener = { v, it ->
            hideKeyboard()
            navController.navigate(
                R.id.action_folderListView_to_bookmarkListView,
                bundleOf(Constant.ID_KEY to it.id)
            )
        }
        adapter.listenerPos = { itemSelectedPos = it }
        allBookmarksBtn.setOnClickListener {
            hideKeyboard()
            navController.navigate(
                R.id.action_folderListView_to_bookmarkListView,
                bundleOf(Constant.ID_KEY to Constant.ALL_BOOKMARK_ID)
            )
        }
        fabFolderList.setOnClickListener {
            navController.navigate(R.id.action_folderListView_to_folderAddView)
        }
    }

    override fun initView() {
        presenter.getPresenterResponse()
    }

    private var itemSelectedPos = -1
    override fun onContextItemSelected(item: MenuItem): Boolean {
        presenter.deleteFolderPresenterResponse(adapter.list[itemSelectedPos].id)
        return super.onContextItemSelected(item)
    }

    fun onFolderDeleted() {
        list.removeAt(itemSelectedPos)
        adapter.apply {
            list = this@FolderListView.list
            notifyItemRemoved(itemSelectedPos)
            notifyItemRangeChanged(itemSelectedPos, adapter.itemCount)
        }
    }
}