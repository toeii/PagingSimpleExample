package com.toeii.pagingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.toeii.pagingexample.db.AppDatabase
import com.toeii.pagingexample.viewmodel.BookViewModel
import com.toeii.roomsimpleexample.R
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity (){

    lateinit var mBaseModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        mBaseModel = ViewModelProviders.of(this)[BookViewModel::class.java]

        if(mBaseModel.getCount() <=0){
            initData()
        }

        val bookAdapter = BookAdapter(this)
        BookRecyclerView.adapter = bookAdapter
        bookAdapter.viewModel = mBaseModel

        mBaseModel.getAllBook.observe(this, Observer { bookAdapter.submitList(it) })

    }

    private fun initData() {
        mBaseModel.insertAll(AppDatabase.CHEESE_DATA)
    }

}
