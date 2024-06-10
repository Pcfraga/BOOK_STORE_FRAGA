package com.example.booksfraga.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val publisher: String,
    val coverImageResId: Int // Altere para Int para armazenar o ID do drawable
)
