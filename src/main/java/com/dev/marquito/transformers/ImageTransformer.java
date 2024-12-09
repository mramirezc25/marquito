package com.dev.marquito.transformers;

import com.dev.marquito.dtos.ImageDTO;
import com.dev.marquito.entities.Image;

public final class ImageTransformer {
	public static Image dtoToImage(ImageDTO dto) {
		Image image = new Image();
		image.setId(dto.getId());
	    image.setName(dto.getName());
	    image.setImageUrl(dto.getImageUrl());
	    image.setImageId(dto.getImageId());
		return image;
	}
	
	public static ImageDTO ImageToDTO(Image image) {
		ImageDTO dto = new ImageDTO();
		dto.setId(image.getId());
		dto.setName(image.getName());
		dto.setImageUrl(image.getImageUrl());
		dto.setImageId(image.getImageId());
		return dto;
	}
}
