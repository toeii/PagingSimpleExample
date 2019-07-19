package com.toeii.pagingexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.toeii.pagingexample.db.AppDatabase
import com.toeii.pagingexample.db.BookBean
import com.toeii.pagingexample.ioThread

class BookViewModel(app: Application): AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).bookDao() //TODO Can not base application?

    companion object {
        private const val PAGE_SIZE = 15
        private const val ENABLE_PLACEHOLDERS = false
    }

    val getAllBook = dao.getAll().toLiveData(Config(
            pageSize = PAGE_SIZE,
            enablePlaceholders = ENABLE_PLACEHOLDERS,
            initialLoadSizeHint = PAGE_SIZE
    ))

    fun insertAll(books: List<String>) = ioThread{
        dao.insertAll(books.map {
            BookBean(uid = 0,bookName = it,isLike = false)
        })
    }

    fun getCount():Int{
        var count = 0
        ioThread{
             count = dao.getDataCount()
        }
        return count
    }

    fun delete(book: BookBean) = ioThread {
         dao.delete(book)
    }



}