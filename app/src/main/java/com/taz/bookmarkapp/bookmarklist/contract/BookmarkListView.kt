package com.taz.bookmarkapp.bookmarklist.contract

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.taz.bookmarkapp.Bookmark
import com.taz.bookmarkapp.R
import com.taz.bookmarkapp.bookmarklist.model.BookmarkListProvider
import com.taz.bookmarkapp.bookmarklist.model.BookmarkListResponseModel
import com.taz.bookmarkapp.helpers.Constant
import com.taz.bookmarkapp.mvp.BaseListFragment
import kotlinx.android.synthetic.main.fragment_bookmark_list.*
import kotlinx.android.synthetic.main.fragment_bookmark_list.view.*


class BookmarkListView : BaseListFragment<BookmarkListResponseModel, Bookmark, BookmarkListAdapter>() {

    override val adapter: BookmarkListAdapter = BookmarkListAdapter()
    override lateinit var recyclerView: RecyclerView

    override fun loadResponse(responseModel: BookmarkListResponseModel) {
//        println(responseModel.folderInfo)
        responseModel.folderInfo?.let {
            baseActivity.title = it.name
            list = it.bookmarkList!!.toMutableList()
            adapter.list = list
            adapter.notifyDataSetChanged()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = recyclerViewBookmarkListFragment
        super.onViewCreated(view, savedInstanceState)
    }

    override val layoutId: Int = R.layout.fragment_bookmark_list
    private lateinit var presenter: BookmarkListPresenter
    private lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val folderId = arguments!!.getInt(Constant.ID_KEY)
        presenter = BookmarkListPresenter(this, BookmarkListProvider(folderId))
        presenter.initPresenter()
        navController = view!!.findNavController()
        val searchView = view!!.searchViewBookmarkList
        searchView.hasListener { o, s -> o.url.toLowerCase().contains(s) }
        adapter hasNoDataDisplay (noDataImageNameIdRv)
        adapter.listenerPos = { itemSelectedPos = it }
        adapter.listener = { _, it ->
            hideKeyboard()
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(browserIntent)
        }
        fabBookmarkList.setOnClickListener {
            navController.navigate(
                R.id.action_bookmarkListView_to_bookmarkAddView,
                bundleOf(Constant.ID_KEY to folderId)
            )
        }
    }

    override fun initView() {
        presenter.getPresenterResponse()
    }

    private var itemSelectedPos = -1
    override fun onContextItemSelected(item: MenuItem): Boolean {
        presenter.deleteBookmarkPresenterResponse(adapter.list[itemSelectedPos].id)
        return super.onContextItemSelected(item)
    }

    fun onBookmarkDeleted() {
        list.removeAt(itemSelectedPos)
        adapter.apply {
            list = this@BookmarkListView.list
            notifyItemRemoved(itemSelectedPos)
            notifyItemRangeChanged(itemSelectedPos, adapter.itemCount)
        }
    }

}