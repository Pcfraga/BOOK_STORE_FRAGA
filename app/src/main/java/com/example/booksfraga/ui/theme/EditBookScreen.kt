package com.example.booksfraga.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.booksfraga.data.Book
import com.example.booksfraga.viewModel.BookViewModel

@Composable
fun EditBookScreen(
    book: Book,
    viewModel: BookViewModel,
    onBookUpdated: () -> Unit
) {
    var title by remember { mutableStateOf(book.title) }
    var author by remember { mutableStateOf(book.author) }
    var publisher by remember { mutableStateOf(book.publisher) }
    var coverImageResId by remember { mutableStateOf(book.coverImageResId) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Publisher") },
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = coverImageResId),
            contentDescription = "Cover Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        OutlinedTextField(
            value = coverImageResId.toString(),
            onValueChange = { coverImageResId = it.toIntOrNull() ?: book.coverImageResId },
            label = { Text("Cover Image Resource ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val updatedBook = book.copy(title = title, author = author, publisher = publisher, coverImageResId = coverImageResId)
                viewModel.updateBook(updatedBook)
                onBookUpdated()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}
