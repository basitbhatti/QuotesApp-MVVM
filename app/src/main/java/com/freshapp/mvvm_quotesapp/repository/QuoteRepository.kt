package com.freshapp.mvvm_quotesapp.repository

import androidx.lifecycle.LiveData
import com.freshapp.mvvm_quotesapp.db.Quote
import com.freshapp.mvvm_quotesapp.db.QuoteDao

class QuoteRepository(val quoteDao : QuoteDao){

    fun getQuotes() : LiveData<List<Quote>>{
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote){
        quoteDao.insertQuote(quote)
    }


}