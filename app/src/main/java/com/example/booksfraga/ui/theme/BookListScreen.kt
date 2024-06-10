package com.example.booksfraga.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.booksfraga.data.Book
import com.example.booksfraga.viewModel.BookViewModel

@Composable
fun BookListScreen(
    viewModel: BookViewModel,
    navigateToAddBook: () -> Unit,
    navigateToEditBook: (Int) -> Unit
) {
    val books by viewModel.books.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(books) { book ->
                BookItem(
                    book = book,
                    onDelete = { viewModel.deleteBook(it) },
                    onEdit = { navigateToEditBook(it.id) }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Botão de editar
                    Button(
                        onClick = { navigateToEditBook(book.id) },
                        modifier = Modifier.padding(end = 8.dp),
                        content = {
                            Text(text = "Editar", color = Color.White)
                        }
                    )

                    Button(
                        onClick = { viewModel.deleteBook(book) },
                        modifier = Modifier,
                        content = {
                            Text(text = "Excluir", color = Color.White)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(
            onClick = { navigateToAddBook() },
            modifier = Modifier
                .padding(16.dp)
                .zIndex(4f)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Adicionar Livro"
            )
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    onDelete: (Book) -> Unit,
    onEdit: (Book) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .zIndex(4f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = book.coverImageResId),
                contentDescription = "Cover Image",
                modifier = Modifier
                    .size(128.dp)
                    .padding(end = 16.dp)
            )

            Column {
                Text(text = "Title: ${book.title}")
                Text(text = "Author: ${book.author}")
                Text(text = "Publisher: ${book.publisher}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}



