package com.dev.marquito.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	private Long id;
    private String title;
    private String author;
    private Integer pages;
    private Double price;
    private Long imageId;
}
