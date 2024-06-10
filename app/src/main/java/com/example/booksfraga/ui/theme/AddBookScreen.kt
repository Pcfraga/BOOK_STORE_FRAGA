package com.example.booksfraga.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.booksfraga.R
import com.example.booksfraga.data.Book
import com.example.booksfraga.viewModel.BookViewModel


@Composable
fun AddBookScreen(navController: NavHostController, viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var coverImageResId by remember { mutableStateOf(R.drawable.livro1) }

    // Default cover image

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
            onValueChange = { coverImageResId = it.toIntOrNull() ?: R.drawable.livro1 },
            label = { Text("Cover Image Resource ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.addBook(
                    Book(
                        title = title,
                        author = author,
                        publisher = publisher,
                        coverImageResId = coverImageResId
                    )
                )
                navController.navigateUp()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Book")
        }
    }
}
