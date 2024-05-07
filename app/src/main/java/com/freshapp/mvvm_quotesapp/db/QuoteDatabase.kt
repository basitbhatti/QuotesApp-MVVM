package com.freshapp.mvvm_quotesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getDao() : QuoteDao


    companion object {
        private var INSTANCE : QuoteDatabase? = null

        fun getInstance(context : Context) : QuoteDatabase{

            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, QuoteDatabase::class.java, "quotesdb")
                    .createFromAsset("quotes.db").build()
            }

            return INSTANCE!!
        }

    }


}