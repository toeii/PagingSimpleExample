package com.toeii.pagingexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toeii.pagingexample.db.BookBean
import com.toeii.pagingexample.viewmodel.BookViewModel
import com.toeii.roomsimpleexample.R
import kotlinx.android.synthetic.main.view_list_book_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class BookAdapter(private var context: Context): PagedListAdapter<BookBean,RecyclerView.ViewHolder>(diffCallback) {

    var viewModel: BookViewModel? = null

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookBean>() {
            override fun areItemsTheSame(oldItem: BookBean, newItem: BookBean): Boolean =
                oldItem.uid == newItem.uid

            override fun areContentsTheSame(oldItem: BookBean, newItem: BookBean): Boolean =
                oldItem == newItem
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_list_book_item,null)
        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book= currentList?.get(position)
        if(null != book){
            holder.itemView.tv_book_title.text = book.bookName
            holder.itemView.iv_delete_icon.onClick {
                viewModel?.delete(book)
            }
        }
    }

    class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}