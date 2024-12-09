package com.dev.marquito.services;

import org.springframework.web.multipart.MultipartFile;

import com.dev.marquito.dtos.ImageDTO;
import com.dev.marquito.entities.Image;

import java.io.IOException;

public interface ImageService {
	ImageDTO uploadImage(MultipartFile file) throws IOException;
    void deleteImage(ImageDTO image) throws IOException;
    Image findById(Long Id);
}
