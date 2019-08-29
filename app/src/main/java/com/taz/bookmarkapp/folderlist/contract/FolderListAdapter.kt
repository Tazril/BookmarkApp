package com.taz.bookmarkapp.folderlist.contract

import com.taz.bookmarkapp.Folder
import com.taz.bookmarkapp.R
import com.taz.bookmarkapp.mvp.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_rv_fragment_folder_list.view.*

class FolderListAdapter : BaseRecyclerAdapter<Folder>(R.layout.item_rv_fragment_folder_list) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.nametextViewFolderList.text = list[position].name
        holder.itemView.cardContainerNameId.setOnCreateContextMenuListener { menu, _, _ ->
            menu.add("Delete Folder")
        }
    }

}
