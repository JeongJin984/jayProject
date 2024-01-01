package com.jay.reviewserver.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type")
@Table(name = "review_image")
public abstract class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_path")
    private String path;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime = LocalDateTime.now();

    protected String getPath() {
        return path;
    }

    protected LocalDateTime getUploadTime() {
        return uploadTime;
    }

    abstract String url();
    abstract Boolean hasThumbnail();
    abstract String getThumbnail();
}
