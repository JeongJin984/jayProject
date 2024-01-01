package com.jay.reviewserver.domain.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EI")
@Access(AccessType.FIELD)
public class ExternalImage extends ReviewImage {

    @Override
    public String url() {
        return getPath();
    }

    @Override
    public Boolean hasThumbnail() {
        return false;
    }

    @Override
    public String getThumbnail() {
        return "";
    }
}
