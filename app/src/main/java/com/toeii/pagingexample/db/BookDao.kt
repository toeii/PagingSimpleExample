package com.toeii.pagingexample.db

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface BookDao {

    @Insert
    fun insertAll(books: List<BookBean>)

    @Delete
    fun delete(book: BookBean)

    @Update
    fun updateBook(book: BookBean)

    @Query("SELECT * FROM bookbean")
    fun getAll(): DataSource.Factory<Int,BookBean>

    @Query("SELECT * FROM bookbean WHERE is_like IN (:isLike)")
    fun getAllByLike(isLike: Boolean): List<BookBean>


    @Query("SELECT COUNT(*) FROM bookbean ")
    fun getDataCount(): Int


}