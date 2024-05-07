package com.freshapp.mvvm_quotesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Quote (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val text : String,
    val author : String
)