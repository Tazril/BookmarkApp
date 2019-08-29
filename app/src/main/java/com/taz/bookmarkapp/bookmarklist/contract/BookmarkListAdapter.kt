package com.taz.bookmarkapp.bookmarklist.contract

import android.view.animation.AnimationUtils
import com.taz.bookmarkapp.Bookmark
import com.taz.bookmarkapp.R
import com.taz.bookmarkapp.mvp.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_rv_fragment_bookmark_list.view.*

class BookmarkListAdapter : BaseRecyclerAdapter<Bookmark>(R.layout.item_rv_fragment_bookmark_list) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.nametextViewBookamrkList.text = list[position].url
        holder.itemView.cardContainerNameId.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_slide_in_left)
        holder.itemView.cardContainerNameId.setOnCreateContextMenuListener { menu, _, _ ->
            menu.add("Delete Bookmark")
        }
    }

}
