package com.dev.marquito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.dev.marquito.dtos.BookDTO;
import com.dev.marquito.services.impl.BookServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@CrossOrigin("http://localhost:4200/")
public class BookController {
    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestPart("book") BookDTO book, @RequestPart("file")MultipartFile file) {
        try {
        	BookDTO savedBook = bookServiceImpl.saveBook(book, file);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<BookDTO> updateBookImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
    	BookDTO book = bookServiceImpl.getBookById(id);
        if (book.getId()!=null) {
        	BookDTO updatedBook = bookServiceImpl.updateBookImage(file, book.get());
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO book){
        try {
        	BookDTO savedBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return new ResponseEntity<>(bookServiceImpl.getBooks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
    	BookDTO book = bookServiceImpl.getBookById(id);
    	if (book.getId()!=null){
    		return new ResponseEntity<>(book, HttpStatus.OK);
    	}else {
    		new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
                
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws IOException {
    	BookDTO book = bookServiceImpl.getBookById(id);
        if (book.getId()!=null){
            bookServiceImpl.deleteBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
