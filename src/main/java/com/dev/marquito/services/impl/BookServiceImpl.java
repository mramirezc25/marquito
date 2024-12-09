package com.dev.marquito.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.marquito.dtos.BookDTO;
import com.dev.marquito.dtos.ImageDTO;
import com.dev.marquito.entities.Book;
import com.dev.marquito.entities.Image;
import com.dev.marquito.repositories.BookRepository;
import com.dev.marquito.repositories.ImageRepository;
import com.dev.marquito.services.BookService;
import com.dev.marquito.services.ImageService;
import com.dev.marquito.transformers.BookTransformer;
import com.dev.marquito.transformers.ImageTransformer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService{


    private final BookRepository bookRepository;
    private final ImageService imageService;

    public BookServiceImpl(BookRepository bookRepository, ImageService imageService) {
        this.bookRepository = bookRepository;
        this.imageService = imageService;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDto, MultipartFile file) throws IOException {
    	Book book = BookTransformer.dtoToBook(bookDto);
    	if (file != null && !file.isEmpty()){
            var imgDto = imageService.uploadImage(file);
            book.setImage(ImageTransformer.dtoToImage(imgDto));
        }
        var bookSaved = bookRepository.save(book);
        return BookTransformer.bookToDTO(bookSaved);
    }

    @Override
    public BookDTO updateBook(BookDTO dto){
    	Book book = BookTransformer.dtoToBook(dto);
    	book.setImage(imageService.findById(dto.getImageId()));
    	var bookSaved = bookRepository.save(book);
        return BookTransformer.bookToDTO(bookSaved);
    }
    @Override
    public List<BookDTO> getBooks(){
        return bookRepository.findAll()
        		.stream()
        		.map(BookTransformer::bookToDTO)
        		.toList();
    }
    @Override
    public BookDTO getBookById(Long id){
        return BookTransformer.bookToDTO(bookRepository.findById(id).orElse(new Book()));
    }
    
    @Override
    public Book findById(Long id){
        return bookRepository.findById(id).orElse(new Book());
    }
    @Override
    public void deleteBook(BookDTO dto) throws IOException {
        if (dto.getImageId() != null) {        	
            imageService.deleteImage(imageService.findById(dto.getImageId()));
        }
        bookRepository.deleteById(dto.getId());
    }
    @Override
    public BookDTO updateBookImage(MultipartFile file, BookDTO dto) throws IOException {
    	if (dto.getImageId() != null) {        	
            imageService.deleteImage(imageService.findById(dto.getImageId()));
        }
        var newImageDto = imageService.uploadImage(file);
        var book = BookTransformer.dtoToBook(dto);
        book.setImage(imageService.findById(newImageDto.getId()));
        return BookTransformer.bookToDTO(bookSaved);
    }
}

