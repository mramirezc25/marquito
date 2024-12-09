package com.dev.marquito.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.marquito.dtos.ImageDTO;
import com.dev.marquito.entities.Image;
import com.dev.marquito.repositories.ImageRepository;
import com.dev.marquito.services.CloudinaryService;
import com.dev.marquito.services.ImageService;
import com.dev.marquito.transformers.ImageTransformer;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageDTO uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        String imageId = (String) uploadResult.get("public_id");
        Image image = new Image(file.getOriginalFilename(), imageUrl, imageId);
        var imageSaved = imageRepository.save(image);
        return ImageTransformer.ImageToDTO(imageSaved);
    }

    @Override
    public void deleteImage(ImageDTO dto) throws IOException {
        cloudinaryService.delete(dto.getImageId());
        imageRepository.deleteById(dto.getId());
    }
    
    @Override
    public Image findById(Long id) {
    	return imageRepository.findById(id).orElse(new Image());
    }

}
