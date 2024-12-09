package com.dev.marquito.transformers;

import com.dev.marquito.dtos.BookDTO;
import com.dev.marquito.entities.Book;
import com.dev.marquito.entities.Image;

public final class BookTransformer {
	public static Book dtoToBook(BookDTO dto) {
		Book book = new Book();
		book.setId(dto.getId());
	    book.setTitle(dto.getTitle());
	    book.setAuthor(dto.getAuthor());
	    book.setPages(dto.getPages());
	    book.setPrice(dto.getPrice());
		return book;
	}
	
	public static BookDTO bookToDTO(Book book) {
		BookDTO dto = new BookDTO();
		dto.setId(book.getId());
		dto.setTitle(book.getTitle());
		dto.setAuthor(book.getAuthor());
		dto.setPages(book.getPages());
		dto.setPrice(book.getPrice());
		if(book.getImage()!=null) {
			dto.setImageId(book.getImage().getId());
		}		
		return dto;
	}
}
