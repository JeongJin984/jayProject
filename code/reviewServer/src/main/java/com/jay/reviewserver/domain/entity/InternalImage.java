package com.jay.reviewserver.domain.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("II")
@Access(AccessType.FIELD)
public class InternalImage extends ReviewImage {
    @Override
    public String url() {
        return "/images/original/" + getPath();
    }

    @Override
    public Boolean hasThumbnail() {
        return true;
    }

    @Override
    public String getThumbnail() {
        return "/images/original/" + getPath();
    }
}
