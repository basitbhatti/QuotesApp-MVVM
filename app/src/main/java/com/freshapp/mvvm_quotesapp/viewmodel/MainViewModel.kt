package com.freshapp.mvvm_quotesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshapp.mvvm_quotesapp.db.Quote
import com.freshapp.mvvm_quotesapp.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: QuoteRepository) : ViewModel(){


    fun getQuotes() : LiveData<List<Quote>> {
        return repository.getQuotes()
    }

    fun insertQuote(quote: Quote){
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertQuote(quote)
        }
    }

}