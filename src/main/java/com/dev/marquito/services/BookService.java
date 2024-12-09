package com.dev.marquito.services;

import org.springframework.web.multipart.MultipartFile;

import com.dev.marquito.dtos.BookDTO;
import com.dev.marquito.entities.Book;

import java.io.IOException;
import java.util.List;


public interface BookService {
	BookDTO saveBook(BookDTO book, MultipartFile file) throws IOException;
	BookDTO updateBook(BookDTO book);
    List<BookDTO> getBooks();
    public Book findById(Long id);
    BookDTO getBookById(Long id);
    void deleteBook(BookDTO book) throws IOException;
    BookDTO updateBookImage(MultipartFile file, BookDTO book) throws IOException;
}
