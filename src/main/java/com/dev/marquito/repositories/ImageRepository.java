package com.dev.marquito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.marquito.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
